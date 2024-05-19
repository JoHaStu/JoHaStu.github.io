<template>
    <div class="page-container">
    
        <HeaderBar />

    <div id="NewProduct" class="insertdiv">
	
    <div style="font-size: smaller;" class="tabcontent">
    <form @submit.prevent="addComment()" method="post" class="form-container">

     <h2>Add a Comment</h2>
 
     <label>Comment</label>
     <textarea type="text" rows="8" placeholder="Whatcha thinking?..." v-model="this.result.comm_content"></textarea>
         
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
        boardid: '',
        comm_content: '',
        comreg_id: '',
        
    },
      username: ''
    }
},

created(){
    console.log(this.$store.state.boardItem)
    this.username = this.$store.state.SessionUser;
    this.result.boardid = this.$store.state.boardItem.boardid;
    },

methods:{
    addComment(){
        axios.post("/api/addComment", {
        boardid: this.result.boardid,
        comm_content: this.result.comm_content,
        comreg_id: this.username,
        
      })
      .then((response) => (response.data = this.$store.state.commItem))
            console.log(this.$store.state.commItem)
            this.$router.push({name: 'DetailView'})
      //this.$router.push({name: 'DetailView'}, this.result.boardid);
      
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