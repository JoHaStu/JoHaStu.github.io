<template>
    <div>
        <HeaderBar />
        <DtlSessionBar v-on:dtlsearch="searchComments" />

    <div id="detailviewdiv">
<h4>All the Details</h4>
<table  >
	<thead>
	<tr style="text-align: left;" >
        <th colspan="5">{{ this.post.title }}</th>
	</tr>
	</thead>
	
	<tbody  >
    <tr style="text-align: left;">
		<td colspan="5">{{ this.post.content }}</td>
	</tr>	 
    <tr>
		<td style="text-align: left;">{{ this.post.boardid }}</td>
		<td style="text-align: left;">Posted by {{ this.post.reg_id }}</td>
		<td style="text-align: left;">Posted on {{ this.post.reg_date }}</td>
		<td style="text-align: right;"><button v-on:click="goUpdateDtl(this.post)">Update</button></td>
		<td style="text-align: right;"><button v-on:click="goAddComment(this.post)">Add a comment</button></td>

	</tr>	 
    <tr style="background-color: white; border-bottom: none">
        <td colspan="4" ><span class="error" v-if="this.error === true">You can't update other people's posts</span></td>
        <td ><span class="error" v-if="this.error === true" @click="closeError()" style="cursor: pointer;">x</span></td>
    </tr>
	</tbody>	
</table>
</div>

<div id="commentsdiv">
	<h4>Comments</h4>
	
    
	<table >
    <thead style="background-color: white; border-top: none">
        <tr style="background-color: white; border-top: none">
        <td colspan="4"><span class="error" v-if="this.error1 === true">You can't delete other people's posts</span></td>
        <td ><span class="error" v-if="this.error1 === true" @click="closeError1()" style="cursor: pointer;">x</span></td>
    </tr>
    </thead>

     <tbody v-for="item in result" v-bind:key="item.boardid">
     <tr>
		<td style="text-align: left;" colspan="4">{{item.comm_content}}</td> 
    </tr>
    <tr> 
        <td name="commentsid" id="commentsid">{{item.commentsid}}</td>
        <td style="text-align: left;">Posted by {{item.comreg_id}}</td>
        <td style="text-align: left;">Posted on {{item.reg_date}}</td>
        <td style="text-align: right;"><button @click="delcomm(item)">Delete</button></td>
    </tr> 
    <tr style="background-color: white;">
        <td colspan="5"></td>
    </tr>
     </tbody>
     </table>
</div>

</div>
</template>

<script>
import axios from 'axios'
import store from '../store/store'

import HeaderBar from '../components/HeaderBar.vue';
import DtlSessionBar from '../components/DtlSessionBar.vue';

export default{
name: 'DetailView',   

components:{
        HeaderBar,
        DtlSessionBar
    },

data(){
    return {
        result: [],
        
       post: {
            boardid: '',
            title: '',
            content: '',
            reg_id: '',
            reg_date: '',
       },
       error: false,
       error1: false
    }
},
created(){
    console.log(this.$store.state.boardItem);
    this.username = this.$store.state.SessionUser

    //this.boardid = this.$store.state.boardItem.boardid;
   this.post = this.$store.state.boardItem;
  //  this.title = this.$store.state.boardItem.title;
    //this.content = this.$store.state.boardItem.content;
   // this.reg_id = this.$store.state.boardItem.reg_id;
    //this.reg_date = this.$store.state.boardItem.reg_date;
    this.getComments();
},

methods: {

  async getComments() {
    const params = { params: { boardid: this.post.boardid } };
     await axios.get("/api/getComments", params)
        .then((response) => {this.result = response.data;
        console.log(response.data)});
         
        },

        goUpdateDtl(post){
          if(this.username !== this.post.reg_id){
            this.error = true;
          }else{
        store.commit('setBoardItem', post)
        this.$router.push({name: 'UpdateDtl'})}
        },
       
        searchComments(dtlsearchresult){
          this.result = dtlsearchresult;
        },

        goAddComment(post){
            store.commit('setBoardItem', post)
            this.$router.push({name: 'AddComment'})
        },

        delcomm(item){
            if(this.username !== item.comreg_id){
            this.error1 = true;
          } else{
            const params = { params: { commentsid: item.commentsid } }
            axios.get("/api/deleteComm", params);

            this.getComments();
            
          }
        },

        closeError(){
          this.error = false;
        },
        closeError1(){
          this.error1 = false;
        }
}

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
 /*   border-left: 1px solid #444444;
   border-right: 1px solid #444444;*/
}

tr,th,td {
    border-bottom: 1px solid #444444;
    padding: 15px;
    }

th{
    background: rgb(167, 167, 167); 
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

h4 {
    background-color: rgb(175 4 4 / 81%);
    color: white;
    padding: 5px;
}

.error{
  
  color: #cf0e0e;
  text-align: right;
  float: right;

  background-color: white; 
 
  border-bottom: none;
   
}


</style>