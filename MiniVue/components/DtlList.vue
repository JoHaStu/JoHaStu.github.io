<template>
    <div class="page-container">
      

    <div id="dtltablediv" class="dtltablediv">
      
    <table id="tb_dtl">
     
     <thead>
      <tr>
        <th colspan="8" style="padding: 0px">
        <div id="dtlsessnavdiv" class="nav_menu">
         <div class="columnA">
        <!--form id="dtlsearch" method="post"--> <!--DO NOT USE FORM -> TRIES TO DO FORM METHOD AND FUNCTION AT SAME TIME AND THROWS ERROR-->
          <input type="search" id="dtlsearchword" name="dtlsearchword" placeholder="Search by Detail Title" v-model="dtlsearchword">
          <button id="dtlsearch_btn" class="buttons" @click="searchDtl(dtlsearchword)">Search</button>
          <router-link to="/insertDtl"><button class="buttons">Add Detail List Item</button></router-link>
          <!--/form-->
         </div>
        </div> 
       </th>
      </tr> 
    <tr>
         <th>Main Code</th>
         <th>Detail Code</th>
         <th>Detail Name</th>
         <th>Detail Notes</th>
         <th>Input Date</th>
         <th>Update Date</th>
         <th>Update</th>
         <th>Delete</th>
     </tr>
     </thead>
     <tbody>
     <tr v-for="entry in dtlresult" v-bind:key="entry.mst_cd">
         <td >{{entry.mst_cd}}</td>
         <td >{{entry.dtl_cd}}</td>
         <td >{{entry.dtl_name}}</td>
         <td >{{entry.dtl_notes}}</td>
         <td >{{entry.reg_date}}</td>
         <td >{{entry.upd_date}}</td>
         <td><button><a v-on:click="href(entry)">Update</a></button></td>
         <td><button><a @click="del(entry)">Delete</a></button></td>
     </tr>
     </tbody>
     </table>
     </div>
     </div>
</template>

<script>
import store from '../store/store';
//import axios from 'axios';

export default {
  name: 'DtlList',
  components: {
  },
  data() {
    return {
     dtlsearchword: '',
     dtlsearchresult: []
    }
  },
 props:
 //{ 
  ['dtlresult'],
  
 // dtlresult: ['dtlresult'],
 // dtlsarchresult: ['dtlsarchresult']

// },
  created() {
  },
  methods: {
    href(entry) {
        console.log(entry)
        store.commit('setDtlItem', entry)
        this.$router.push({name: 'UpdateDtl'}) 
    },
    del(entry){
        const params = { params: { mst_cd: entry.mst_cd, dtl_cd: entry.dtl_cd} }
       // console.log(params)
        console.log(params)
        //axios.get("/api/deleteDtl", params)
        //.then((response) => (this.dtlresult = response.data))      
       // this.$emit('dtldel', this.params);
       this.$emit('dtldel', params);

      },

  searchDtl(dtlsearchword){
    console.log("dtlsearchword==>" + dtlsearchword);
    this.$emit('dtlsearch', dtlsearchword); 
/*
    searchDtl(dtlsearchword){
      console.log("dtlsearchword==>" + dtlsearchword);
      const params = { params: { paramword: this.dtlsearchword}}
      axios.get("/api/searchDtl", params)
      .then((response) =>
      {
      this.dtlsearchresult = response.data;
      console.log(response.data);
      this.$emit('dtlsearch', this.dtlsearchresult);
      });*/
    }
  }}
</script>

<style scoped>
.page-container {
width: 100%;
height: 100%;
min-height: 100vh;
position: relative;
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
  /*  font-size: smaller;*/
}

.nav_menu {
overflow: hidden;
color: antiquewhite;
font-weight: lighter;
padding: 10px;
background-color: #04442f;
/*width: 93.5%;*/
margin-left: auto; 
margin-right: auto;
}

.columnA{
float: left;
flex: 30%;
}

.buttons {
background-color: #066d06;
font-size: smaller;
color: white;
padding: 5px;
border: none;
cursor: pointer;
margin-left: 5px;
border-radius: 5%;
}
</style>