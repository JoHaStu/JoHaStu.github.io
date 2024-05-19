<template>

    <div>
        <div id="sessnavdiv" class="nav_menu">
          <div class="columnA">
              <router-link to="/"><button class="buttons">Login</button></router-link>
              <router-link to="/register"><button class="buttons">Register</button></router-link>
          </div>
        </div>
    </div>

    </template>
    
    <script>
  
    export default {
      
      name: 'LoginBar',
    
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