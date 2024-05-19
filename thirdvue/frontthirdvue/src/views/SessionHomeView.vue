<template>
    <div>
    
        <!-- <div>
        <div class="header">
        <h2 style="color:white;">
           Third Vue Project
        </h2>
        </div>

        <div id="sessnavdiv" class="nav_menu">
          <div class="columnA">
                 <input type="search" id="searchword" name="searchword" placeholder="Enter Search Term" v-model="searchword">
                <button id="allsearch_btn" @click="searchAll(searchword, searchoptions)">Search</button>
          </div>
          <div>
            ...
          </div>
        </div>  
    </div> -->
    
    <HeaderBar />
    <SessionBar v-on:search="searchlist" />

      <table>
         <thead>
        <tr>
             <th>Product Code</th>
             <th>Product Name</th>
             <th>Lot Number</th>
             <th>Quantity</th>
             <th>Production Date</th>
             <th>End Date</th>
             <th>Update</th>
             <th>Delete</th>
          
         </tr>
         </thead>
         <tbody>
         <tr v-for="allitem in allresult" v-bind:key="allitem.prodcode">
             <td>{{allitem.prodcode}}</td>    
             <td>{{allitem.prodname}}</td>
             <td>{{allitem.lotno}}</td>
             <td>{{allitem.qty}}</td>
             <td>{{allitem.proddate}}</td>
             <td>{{allitem.enddate}}</td>   
             <td><button v-on:click="goUpdate(allitem)">Update</button></td>
             <td><button @click="del(allitem)">Delete</button></td> 
         </tr>
         </tbody>
     </table>  
    </div>
    </template>
    
    <script>
    import axios from 'axios'
    import store from '../store/store';
    import HeaderBar from '../components/HeaderBar.vue';
    import SessionBar from '../components/SessionBar.vue';
    
    export default {
    name: 'SessionHomeView',

       components:{
           HeaderBar,
           SessionBar
        },
    
    data() {
        return {
            
        //  searchword: '',
          allresult: [],
        
        }
      },
      created() {
        //console.log('List', this.$store.state.user)
        this.getAllData()
       // this.username = this.$store.state.SessionUser
      },
      //props:['mstSearchRes'],
      methods: {
        getAllData() {
          axios
            .get("api/list")
            .then((response) => (this.allresult = response.data))
        },
    
        goUpdate(allitem){
          
        store.commit('setInvItem', allitem)
        this.$router.push({name: 'UpdateForm'})},

        del(allitem){
          // if(this.username !== item.reg_id){
          //   this.error = true;
          // } else{
            const params = { params: { prodcode: allitem.prodcode } }
            axios.get("/api/delete", params);
            axios.get("api/list").then((response) => (this.allresult = response.data)); 

          },

             searchlist(searchresult){ 
             console.log(searchresult);
             this.allresult = searchresult;
    },
        },

        // searchAll(searchword, searchoptions){
        //     console.log(searchword, searchoptions);
        //     const params = { params: { paramword: this.searchword, searchoptions: searchoptions}}
        //     console.log(params);
        //     axios        
        //     .get("api/searchAll", params)
        //     .then((response) => {
        //         this.allresult = response.data;
        //         console.log(response.data);
        // });
        // }
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
    .header {
    background-color: rgb(126, 2, 2);
    padding: 15px;
    text-align: center; 
    /*width: 100%;*/
    margin-top: 0px;
    }
    
    .nav_menu {
    overflow: hidden;
    color: antiquewhite;
    font-weight: lighter;
    padding: 10px;
    background-color: #04442f;
    /*width: 100%;*/
    margin-bottom: 30px;
    /*margin-left: auto;
    margin-right: auto;*/
    }
    
    .columnA{
    float: left;
    flex: 30%;
    }
    
    </style>