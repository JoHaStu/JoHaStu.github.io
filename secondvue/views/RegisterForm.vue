<template>
<div>
    <HeaderBar />
    <div class="columnA"> 
      <div class="tabcontent">
      <form @submit.prevent="register()" class="form-container">
       <h2>Sign Up</h2>
    
       <label for="username"><b>Your Name</b></label>
       <input type="text" placeholder="Your Name" v-model="this.registerdetails.entername1">

       <label for="userid"><b>New Username</b></label>
       <input type="text" placeholder="Enter Username" v-model="this.registerdetails.enterid1">
       <span name="error1" v-if="this.error1 === true" class="error">That username is taken </span>
       <span class="error" v-if="this.error1 === true" @click="closeError1()" style="cursor: pointer;"> x</span><br><br>

       <label for="pw"><b>New Password</b></label>
       <input type="password" placeholder="Enter Password" name="enterpw1" v-model="this.registerdetails.enterpw1">

       <label for="confirmpw"><b>Confirm Password</b></label>
       <input type="password" placeholder="Re-enter Password" name="confirmpw" v-model="this.registerdetails.confirmpw">
       <span name="error2" v-if="this.error2 === true" class="error">The passwords don't match  </span>
       <span class="error" v-if="this.error2 === true" @click="closeError2()" style="cursor: pointer;"> x</span><br><br>

       <button type="submit" class="btn">Register</button>
    
     </form>
     </div>
     
    </div>
</div>
    </template>
    
    <script>
    import axios from 'axios';
    import HeaderBar from '../components/HeaderBar.vue';

    export default {
    name: 'RegisterForm',

    components:{
        HeaderBar
    },

    
        data() {
        return {
          registerdetails: {
            enterid1: '',
            entername1: '',
            enterpw1: '',
            confirmpw: '',
            test_result: ''
          
        },
          error1: false,
          error2: false,
          result:  ''
        }
    },
    
      methods:{
        
       register(){
        console.log("password: " + this.registerdetails.enterpw1)
       axios.post("/api/register1", {
        enterid1: this.registerdetails.enterid1,
        entername1: this.registerdetails.entername1,
        enterpw1: this.registerdetails.enterpw1,
        confirmpw: this.registerdetails.confirmpw
      //  test_result: this.registerdetails.test_result
      },
        {
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          }
        }).then( (response) =>(this.result = String(response.data)));
      
    if(this.result  === "success"){
       
      this.$router.push({name: 'LoginForm'});
      } else if(this.result === "usertaken"){
          this.error1 = true;

        }else if(this.result === "pwnomatch"){       
          this.error2 = true;
        }
        },

        closeError1(){
          this.error1 = false;
        },

        closeError2(){
          this.error2 = false;
        }
      }
    
    }
    
    </script>
    
    <style scoped>
    
    .page-container {
    width: 100%;
    height: 100%;
    min-height: 100vh;
    position: relative;
    }
    
    a {
    color: antiquewhite;
    text-decoration: none;
    }
    
    .columnA{
    float: left;
    flex: 30%;
    }
    
    .form-container {
      max-width: 300px;
      padding: 20px;
      background-color: white;
    }
    
    .form-container input[type=text], .form-container input[type=password]{
      width: 92%;
      padding: 15px;
      margin: 5px 0 22px 0;
      border: none;
      background: #f1f1f1;
    }
    
    .form-container input[type=text]:focus, .form-container input[type=password]:focus {
      background-color: #ddd;
      outline: none;
    }
    
    .form-container .btn {
      background-color: #04442f;
      color: white;
      padding: 16px 20px;
      border: none;
      cursor: pointer;
      width: 100%;
      margin-bottom:10px;
      margin-top: 10px;
    }
    
    .form-container .btn:hover, .open-button:hover {
      opacity: 1;
    }
    
    .tabcontent {
      width: 100%;
      padding: 6px 6px;
      border: 1px solid #ccc;
      box-align: center;
      margin-top: 30px;
    
    }
    
    
    .error{
      
      color: #cf0e0e;
      text-align: center;
      margin-bottom: 10px;
       
    }
    
    </style>
    