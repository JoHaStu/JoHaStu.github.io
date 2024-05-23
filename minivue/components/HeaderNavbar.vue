<template>
<div>
   <!-- <div class="header">
      <router-link to="/list"><h2 style="color:white;"><a>Mini Vue Project</a></h2></router-link>
    </div>-->
    <div id="sessnavdiv" class="nav_menu">
      <div class="columnA">
        <!--form id="search" method="post"--> <!--DO NOT USE FORM WITH FUNCTIONS INSIDE-->
          <input type="search" id="searchword" name="searchword" placeholder="Search Product by Title" v-model="searchword">
          <button id="search_btn" class="buttons" @click="searchMst(searchword)">Search</button>
          <router-link to="/insertMst"><button class="buttons">Add Master List Item</button></router-link>
          <router-link to="/getAllCombined"><button class="buttons">Combined Overview</button></router-link>
        <!--/form-->
      </div>
    </div>
    
</div>
</template>

<script>
import axios from 'axios';


export default {
  
  name: 'HeaderNavbar',

  components: { 
   
  },


  data(){
    return {
     
      searchword: '',
      searchresult : []
      
        };

  },

  methods:{
  
      searchMst(searchword){
      console.log("searchword==>" + searchword);
      const params = { params: { paramword: this.searchword}}
      axios.get("/api/searchMst", params)
      .then((response) =>
      {
      this.searchresult = response.data;
      console.log(response.data);
      this.$emit('search', this.searchresult);
      });
      
     }

}
}
</script>



<style scoped>
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

a {
color: antiquewhite;
text-decoration: none;
}
</style>