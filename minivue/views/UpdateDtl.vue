<template>
    <div class="page-container">
       <HomeHeader/>
   
       <div id="UpdProduct" class="updatediv">
       
       <div id="updateFormDiv" style="font-size: smaller;" class="tabcontent">
       <form id="updateForm" method="post" class="form-container" @submit.prevent="updateDtl()"> 
   
        <h2>Update Item <span id="mstdtl_cd" name="mstdtl_cd">{{ $store.state.dtlItem.mst_cd }} {{ $store.state.dtlItem.dtl_cd }}</span></h2>
    
        <label>Name</label>
        <input style="margin-bottom: 10px; padding: 10px;" type="text" placeholder="Update Name" id="dtl_name" name="dtl_name" v-model="this.result.dtl_name">
        
        <label>Notes</label>
        <input style="margin-bottom: 10px; padding: 10px;" type="text" placeholder="Update Notes" id="dtl_notes" name="dtl_notes" v-model="this.result.dtl_notes">
            
        <button type="submit" class="btn" >Update</button>
      </form>
      </div>
      </div>
   
      </div>
   </template>
   
   <script>
   import axios from 'axios';
   import HomeHeader from '../components/HomeHeader.vue';

   export default {
    components:{
      HomeHeader
    },

    data() {
       return {
         result: {
           dtl_name: '',
           dtl_notes: ''
       }
       }
     },
   created(){
      console.log(this.$store.state.dtlItem)
      this.result = this.$store.state.dtlItem
   },
   methods:{
      updateDtl(){
           axios.post("/api/updateDtl", {
           mst_cd: this.$store.state.dtlItem.mst_cd,
           dtl_cd: this.$store.state.dtlItem.dtl_cd,
           dtl_name: this.result.dtl_name,
           dtl_notes: this.result.dtl_notes,
         })
   
           .then((response) => (response.data = this.$store.state.dtlItem))
           console.log(this.$store.state.dtlItem)
           this.$router.push({name: 'ListView'}, )
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
   
   .header {
   background-color: rgb(126, 2, 2);
   padding: 15px;
   text-align: center; 
   width: 100%;
   margin-top: 0px;
   }
   
   .form-container {
     max-width: 300px;
     padding: 20px;
     background-color: white;
   }
   
   .form-container input[type=text], .form-container input[type=number]{
     width: 92%;
     padding: 15px;
     margin: 5px 0 22px 0;
     border: none;
     background: #f1f1f1;
   }
   
   .form-container input[type=text]:focus, .form-container input[type=number]:focus {
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
     width: 22%;
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