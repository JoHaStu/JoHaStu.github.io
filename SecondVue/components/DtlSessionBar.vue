<template>

    <div>
        <div id="sessnavdiv" class="nav_menu">
          <div class="columnA">
           <input type="search" name="searchword" placeholder="Search for a Comment" v-model="searchword">
           <button class="buttons" @click="searchComments(searchword)">Search</button>
          </div>
          <div class="columnB">
              <span style="font-size: smaller;">Logged in as: {{ username }} </span>
              <button class="buttons" style="margin-left: 5px;" @click="logout()">Logout</button>
          </div>
          
        </div>
    </div>

    </template>
    
    <script>
    import axios from 'axios';
    import store from '@/store/store';
    
    export default {
      
      name: 'DtlSessionBar',
    
      components: { 
       
      },
    
     

      data(){
        return {
         
         username: '',
         searchword: '',
         dtlsearchresult: []
            };
    
      },
      created(){
       console.log(this.$store.state.SessionUser)
       this.username = this.$store.state.SessionUser
},
    
      methods:{
        
        searchComments(searchword){
          console.log("searchword==>" + searchword);
          const params = { params: { paramword: this.searchword}}
          axios.get("/api/searchComments", params)
          .then((response) =>
          {
          this.dtlsearchresult = response.data;
          console.log(response.data);
          this.$emit('dtlsearch', this.dtlsearchresult);
        });
        },

        async logout(){

          await axios.get("/api/logout")
          store.commit('setSessionUser', null)
          this.$router.push({name: 'home'})
          console.log(this.$store.state.SessionUser)
          }
    }
    }
    </script>
    
    
    
    <style scoped>
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

    .columnB{
      float: right;
      text-align: right;
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