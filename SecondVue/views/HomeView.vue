<template>
    <div>
      
        <HeaderBar />
        <LoginBar />

<table>
  <thead>
    <tr>
         <th>Post Number</th>
         <th>Title</th>
         <th>Posted By</th>
         <th>Posted On</th>
     </tr> 
  </thead>

  <tbody>
     <tr v-for="item in result" v-bind:key="item.boardid">
        <td>{{item.boardid}}</td>
        <td>{{item.title}}</td>
        <td>{{item.reg_id}}</td>
        <td>{{item.reg_date}}</td>
     </tr>
  </tbody>  
</table>


    </div>
  </template>
  
<script>
  import axios from 'axios';
  import HeaderBar from '../components/HeaderBar.vue';
  import LoginBar from '../components/LoginBar.vue' 

  export default {
    name: 'HomeView',
    components:{
        HeaderBar,
        LoginBar
    },
    data() {
    return {
      result: [],
      username: ''
    }
  },
    created() {
    this.getData(),
    this.username = this.$store.state.SessionUser
  },

  methods: {
    getData() {
      axios
        .get("api/list")
        .then((response) => (this.result = response.data))
        .catch((error) => console.log(error))
    },
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

</style>