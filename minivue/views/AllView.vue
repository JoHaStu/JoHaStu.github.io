<template>
<div>

    <div>
    <div class="header">
    <h2 style="color:white;">
        Mini Vue Project
    </h2>
    </div>
    <div id="sessnavdiv" class="nav_menu">
      <div class="columnA">
        <label for="searchoptions" style="font-size: smaller">Search for:</label>
			<select v-model="searchoptions" name="searchoptions" id="searchoptions" style="margin-right: 5px; margin-left: 5px">
			  <option value="1">Master Name</option>
			  <option value="2">Master Note</option>
			  <option value="3">Detail Name</option>
			  <option value="4">Detail Note</option>
			</select>	
	 		<input type="search" id="searchword" name="searchword" placeholder="Enter Search Term" v-model="searchword">
			<button id="allsearch_btn" @click="searchAll(searchword, searchoptions)">Search</button>
      </div>
    </div>
   
</div>

  <table>
     <thead>
    <tr>
         <th>Master Code</th>
         <th>Master Name</th>
         <th>Master Notes</th>
         <th>Master Input Date</th>
         <th>Master Update Date</th>
         <th>Detail Code</th>
         <th>Detail Name</th>
         <th>Detail Notes</th>
         <th>Detail Input Date</th>
         <th>Detail Update Date</th>
     </tr>
     </thead>
     <tbody>
     <tr v-for="allitem in allresult" v-bind:key="allitem.mst_cd">
	     <td>{{allitem.mst_cd}}</td>    
         <td>{{allitem.mst_name}}</td>
         <td>{{allitem.mst_notes}}</td>
         <td>{{allitem.mst_reg_date}}</td>
         <td>{{allitem.mst_upd_date}}</td>
         <td>{{allitem.dtl_cd}}</td>    
         <td>{{allitem.dtl_name}}</td>
         <td>{{allitem.dtl_notes}}</td>
         <td>{{allitem.dtl_reg_date}}</td>
         <td>{{allitem.dtl_upd_date}}</td>
     </tr>
     </tbody>
 </table>  
</div>
</template>

<script>
import axios from 'axios'

export default {
name: 'AllView',

data() {
    return {
        
      searchword: '',
      allresult: [],
   
    }
  },
  created() {
    //console.log('List', this.$store.state.user)
    this.getAllData()
  },
  //props:['mstSearchRes'],
  methods: {
    getAllData() {
      axios
        .get("api/getAllCombined")
        .then((response) => (this.allresult = response.data))
    },

    searchAll(searchword, searchoptions){
        console.log(searchword, searchoptions);
        const params = { params: { paramword: this.searchword, searchoptions: searchoptions}}
        console.log(params);
        axios        
        .get("api/searchAll", params)
        .then((response) => {
            this.allresult = response.data;
            console.log(response.data);
    });
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