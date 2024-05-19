<template>
    <div>
      
        <HeaderBar />
        <SessionBar v-on:search="searchPosts" />

<table>
  <thead>
   
    <tr>
         <th>Board ID</th>
         <th>Title</th>
         <th>Posted By</th>
         <th>Posted On</th>
         <th colspan="3">
          <span class="error" v-if="this.error === true">You can't delete other people's posts</span>
          <span class="error" v-if="this.error1 === true">You can't update other people's posts</span>
        </th>

     </tr> 
  </thead>

  <tbody>
     <tr v-for="item in result" v-bind:key="item.boardid">
        <td>{{item.boardid}}</td>
        <td>{{item.title}}</td>
        <td>{{item.reg_id}}</td>
        <td>{{item.reg_date}}</td>
        <td><button v-on:click="goDetails(item)">Details</button></td>
        <td><button v-on:click="goUpdate(item)">Update</button></td>
        <td><button @click="del(item)">Delete</button></td>
     </tr>
  </tbody>  
</table>


    </div>
  </template>
  
<script>
  import HeaderBar from '../components/HeaderBar.vue';
  import SessionBar from '../components/SessionBar.vue';
  
  import axios from 'axios';  
  import store from '../store/store';

  export default {
    name: 'SessionHomeView',
    components:{
        HeaderBar,
        SessionBar
    },

    data() {
    return {
      result: {
        boardid: '',
        title: '',
        content: '',
        reg_id: '',
        reg_date: '',
        test_result: ''
      },
       error: false,
       error1: false,
       username: ''
    }
    },

    created(){
        this.getData(),
        this.username = this.$store.state.SessionUser
    },

    methods:{
        getData() {
        axios.get("api/list")
        .then((response) => (this.result = response.data))
         
        },

        goDetails(item){
        console.log(item)
        store.commit('setBoardItem', item)
        this.$router.push({name: 'DetailView'}) 
    },
        goUpdate(item){
          if(this.username !== item.reg_id){
            this.error1 = true;
          }else{
        store.commit('setBoardItem', item)
        this.$router.push({name: 'UpdateBoard'})}
        },

        /*
        del(item){
          console.log(item);
          
        //const params = { params: { boardid: item.boardid } }
        const response = axios.get("/api/deletePost", item)
        /*const response =  axios.post("/api/deletePost", {
          boardid: this.result.boardid,
          reg_id: this.result.reg_id,
          test_result: this.result.test_result
      }); ==================================*/
     
      /*    console.log(response.data)

        if(response.data.test_result === "success"){
           axios.get("api/list").then((response) => (this.result = response.data)) 
      } else{
          this.error = true;
        }}*/

        del(item){
          if(this.username !== item.reg_id){
            this.error = true;
          } else{
            const params = { params: { boardid: item.boardid } }
            axios.get("/api/deletePost", params);
            axios.get("api/list").then((response) => (this.result = response.data)); 

          }
        },

        searchPosts(searchresult){
          this.result = searchresult;
        }



    },

  }


</script>
  
<style scoped>
  
* {
box-sizing: border-box;
font-family: Arial, Helvetica, sans-serif;
}

table {   
    width: 95%;
    font-size: smaller;
    border-top: 1px solid #444444;
    border-collapse: collapse;
    margin-left: auto;
    margin-right: auto;
    text-align: center;
    background: rgb(216, 216, 216);
    border-left: 1px solid #444444;
    border-right: 1px solid #444444;
}

tr,th,td {
    border-bottom: 1px solid #444444;
    padding: 15px;
    }

th{
    background: rgb(167, 167, 167); 
}

tr:hover {
    background-color: #b0b6b6;
}

button {
background-color: #066d06;
color: white;
font-size: smaller;
padding: 3px;
border: none;
cursor: pointer;
margin-left: 5px;
border-radius: 5%;
}

a {
    color: white;
    text-decoration: none;
    font-size: smaller;
}

.error{
  
  color: #cf0e0e;
  text-align: center;
  margin-bottom: 10px;
  background: rgb(167, 167, 167); 
  font-size: smaller;
   
}


</style>