<template>
    <div class="pagecontainer">
        <HeaderNavbar v-on:search="searchMst"/>

    
    <div class="msttablediv">
    <table>
    <thead>
    <tr>
        <th>Select</th>  
        <th>Code</th>
        <th>Name</th>
        <th>Notes</th>
        <th>Input Date</th>
        <th>Update Date</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="item in result" v-bind:key="item.mst_cd">
       <td><input type="checkbox" v-on:click="getDtlList(item)"></td>
       <td>{{item.mst_cd}}</td>    
       <td>{{item.mst_name}}</td>
       <td>{{item.mst_notes}}</td>
       <td>{{item.reg_date}}</td>
       <td>{{item.upd_date}}</td>
       <td><button v-on:click="href(item)">Update</button></td>
       <td><button @click="del(item)">Delete</button></td>
    </tr>
    </tbody>
    </table>
    </div>

    <div>
    <DtlList :dtlresult="dtlresult" v-on:dtlsearch="searchDtl" v-on:dtldel="deleteDtl"/>
    </div>
    
    </div>
</template>

<script>
import HeaderNavbar from '../components/HeaderNavbar.vue';
import DtlList from '../components/DtlList.vue';

import store from '../store/store'
import axios from 'axios';
//import {reactive} from 'vue';

export default {
  name: 'ListView',
  
  components: {
    HeaderNavbar,
    DtlList
  },
  data() {
    return {
      result: [],
    
      dtlresult: {
        dtlresult: ['dtlresult'],  
     }
    }
  },
  created() {
    //console.log('List', this.$store.state.user)
    this.getData()
  },
  
  methods: {
    getData() {
      axios
        .get("api/list")
        .then((response) => (this.result = response.data))
        .catch((error) => console.log(error))
    },
    href(item) {
        console.log(item)
        store.commit('setMstItem', item)
        this.$router.push({name: 'UpdateMstView'}) 
    },
    del(item){  
        const params = { params: { mst_cd: item.mst_cd } }
        //console.log(params)
        axios.get("/api/deleteMst", params)
        axios.get("api/list").then((response) => (this.result = response.data)) 
  },
    getDtlList(item) {
      const params = { params: { mst_cd: item.mst_cd } };
      axios.get("api/dtllist", params)
        .then((response) => (this.dtlresult = response.data))
    },

      searchMst(searchresult){ 
    //  console.log(searchresult);
      this.result = searchresult;
    },
      deleteDtl(params){
        console.log(params);
        axios.get("/api/deleteDtl", params)
        .then((response) => {this.dtlresult = response.data;
        console.log(response.data)});
      },

      searchDtl(dtlsearchword){
      console.log("dtlsearchword==>" + dtlsearchword);
      const params = { params: { paramword: dtlsearchword}}
      console.log(params);
      axios.get("/api/searchDtl", params)
      .then((response) =>
      {
      this.dtlresult = response.data;
      console.log(response.data);
    });
    }

    /*
    searchDtl(dtlsearchresult){
      console.log(dtlsearchresult);
      this.dtlresult.dtlresult = dtlsearchresult;
    }*/
  }
}
</script>

<style scoped>
* {
box-sizing: border-box;
font-family: Arial, Helvetica, sans-serif;
}

.page-container {
    width: 100%;
    height: 100%;
    min-height: 100vh;
    position: relative;
    margin-top: 0px;
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

.msttablediv{
margin-bottom: 30px;  
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
</style>