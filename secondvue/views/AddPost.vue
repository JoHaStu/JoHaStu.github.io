<template>
    <div class="page-container">
    
        <HeaderBar />

    <div id="NewProduct" class="insertdiv">
	
    <div style="font-size: smaller;" class="tabcontent">
    <form @submit.prevent="addPost()" method="post" class="form-container">

     <h2>Write about Something...</h2>
 
     <label>Title</label>
     <input style="margin-bottom: 10px; padding: 10px;" type="text" placeholder="What's it all about?" v-model="this.result.title">
     
     <label>Content</label><br>
     <textarea type="text" rows="8" placeholder="Tell us everything..." v-model="this.result.content"></textarea>
         
     <button type="submit" class="btn">Submit</button>
   </form>

   </div>
   </div>

   </div>
</template>

<script>
import axios from 'axios';

import HeaderBar from '../components/HeaderBar.vue';

export default {
    name: 'AddComment',

    components:{
        HeaderBar,
        
    },

 data() {
    return {
      result: {
       // boardid: '',
        title: '',
        content: '',
        reg_id: '',
        
    },
      username: ''
    }
},

created(){
    this.username = this.$store.state.SessionUser;

    },

methods:{
    addPost(){
        axios.post("/api/addPost", {
        title: this.result.title,
        content: this.result.content,
        reg_id: this.username,
        
      })
      .then((response) => (response.data = this.$store.state.boardItem))
            console.log(this.$store.state.boardItem)
            this.$router.push({name: 'SessionHomeView'})
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


.form-container {
  max-width: 300px;
  padding: 20px;
  background-color: white;
}

.form-container input[type=text], .form-container textarea[type=number]{
  width: 92%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background: #f1f1f1;
}

.form-container input[type=text]:focus, .form-container textarea[type=number]:focus {
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
  padding: 6px 12px;
  border: 1px solid #ccc;
  box-align: center;
}

.insertdiv {
  position:absolute;
  z-index: 9999;
  /*border: 3px solid #f1f1f1;*/
  width: 22%;
  margin-top: 20px;
  margin-bottom: 30px;
}

</style>