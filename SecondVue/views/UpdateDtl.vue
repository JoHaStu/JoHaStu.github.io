<template>
    <div class="page-container">
       <HeaderBar />
   
       <div  class="updatediv">
       
       <div  style="font-size: smaller;" class="tabcontent">
       <form  method="post" class="form-container" @submit.prevent="updateDtl()"> 
   
        <h2>Update Post  #<span>{{ $store.state.boardItem.boardid }}</span></h2>
    
        <label>Title</label>
        <input style="margin-bottom: 10px; padding: 10px;" type="text" placeholder="Update Title"  v-model="this.result.title">
        
        <label>Content</label>
        <!--input style="margin-bottom: 10px; padding: 10px;" type="text" placeholder="Update Notes" v-model="this.result.content"-->
        <textarea type="text" placeholder="Update Content" rows="4" v-model="this.result.content"></textarea>  
        <button type="submit" class="btn" >Update</button>
      </form>
      </div>
      </div>
   
      </div>
   </template>
   
   <script>
   import axios from 'axios';

   import HeaderBar from '../components/HeaderBar.vue';
   
   export default {
    name: 'UpdateBoard',
    components:{
        HeaderBar
    },

    data() {
       return {
         result: {
           boardid: '',
           title: '',
           content: ''
       },
       username: ''
       }
     },
   created(){
      console.log(this.$store.state.boardItem)
      this.result = this.$store.state.boardItem
      this.username = this.$store.state.SessionUser
   },
   methods:{
      updateDtl(){
           axios.post("/api/updateBoard", {
            boardid: this.$store.state.boardItem.boardid,
            title: this.result.title,
            content: this.result.content,
         })
            .then((response) => (response.data = this.$store.state.boardItem))
            console.log(this.$store.state.boardItem)
            this.$router.push({name: 'DetailView'})
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
   
   .form-container input[type=text], .form-container textarea[type=text] {
     width: 92%;
     padding: 15px;
     margin: 5px 0 22px 0;
     border: none;
     background: #f1f1f1;
   }
   
   .form-container input[type=text]:focus, .form-container textarea[type=text] {
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
   
   .updatediv {
     position:absolute;
     z-index: 9999;
    /* border: 3px solid #f1f1f1;*/
     width: 40%;
     margin-top: 20px;
     margin-bottom: 30px;
   }
   
   .error{
     font-size: smaller;
     background-color: #066d06;
     color: #fff;
     text-align: center;
     border-radius: 6px;
     padding: 8px;
     margin-left: 8px;
     display: inline-block;
   }
   </style>