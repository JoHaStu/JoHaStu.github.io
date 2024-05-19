<template>
<div>    
    <HeaderBar />

<div class="columnA"> 
  <div class="tabcontent">
  <form id="loginForm" @submit.prevent="login()" class="form-container">
   <h2>Login</h2>

   <label for="username"><b>Username</b></label>
   <input type="text" placeholder="Enter Username" name="id" v-model="this.logindetails.id">

   <label for="pw"><b>Password</b></label>
   <input type="password" placeholder="Enter Password" name="pw" v-model="this.logindetails.pw">
   <span name="error" v-if="this.error === true" class="error">This username and password don't match</span><br><br>

   <button type="submit" class="btn">Login</button>
   <router-link to="/register"><button class="btn" style="background-color: rgb(126, 2, 2)">Register</button></router-link>

 </form>
 </div>
 
</div>
</div>
</template>

<script>
import axios from 'axios';
import HeaderBar from '../components/HeaderBar.vue';
import store from '../store/store';

export default {
  
  name: 'LoginForm',

  components:{
        HeaderBar
    },

    data() {
    return {
      logindetails: {
        id: '',
        pw: '',
        test_result: ''
    },
      error: false,
     
    
   }
  },

  methods:{
    
    async login(){
       const response = await axios.post("/api/login", {
        id: this.logindetails.id,
        pw: this.logindetails.pw,
        test_result: this.logindetails.test_result
      });
      console.log(this.logindetails.id, this.logindetails.pw);
      console.log(response.data);
      
     // console.log(this.error)
      if(response.data.test_result === "success"){
    //  if(this.error === false){    
     // if(this.logindetails.enterid !== null){
      console.log("res===test_result==>", response.data.test_result);
         // this.user = this.logindetails.enterid;
         // console.log(user);
         
          store.commit('setSessionUser', this.logindetails.id)
          this.$router.push({name: 'SessionHomeView'});
      } else{

          console.log("res===test_result===>", response.data.test_result);
          this.error = true;
        }}

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
