<template>
 
        <div class="header">
        <h2><a href="#" @click="goBackHome()">Second Vue Project</a></h2>
        </div>      
 
    </template>
    
    <script>
    
    import store from '@/store/store';
    
    export default {
      
      name: 'HeaderBar',
    
      components: { 
       
      },
    
     

      data(){
        return {
         
         username: ''

            };
    
      },
      created(){
       this.username = this.$store.state.SessionUser
},
    
      methods:{

        goBackHome(){
          store.commit('setSessionUser', this.username)
            this.$router.push({name: 'SessionHomeView'})
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

    a{
text-decoration: none;
color: white;
    }
    
    </style>