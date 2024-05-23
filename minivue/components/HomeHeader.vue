<template>

        <div class="header">
          <router-link to="/list"><h2 style="color:white;"><a>Mini Vue Project</a></h2></router-link>
        </div>
      
    </template>
    
    <script>

    
    
    export default {
      
      name: 'HomeHeader',
    
      components: { 
       
      },
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
    
    a {
    color: antiquewhite;
    text-decoration: none;
    }
    </style>