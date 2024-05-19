<template>
    <div class="page-container">
       <HeaderBar />
   
       <div  class="updatediv">
       
       <div  style="font-size: smaller;" class="tabcontent">
       <form  method="post" class="form-container" @submit.prevent="update()"> 
   
        <h2>Update Product#<span>{{ $store.state.invItem.prodcode }}</span></h2>
  
        <label>Product Name</label>
        <input style="margin-bottom: 10px; padding: 10px;" type="text"  v-model="this.result.prodname">

        <label>Lot Number</label>
        <input style="margin-bottom: 10px; padding: 10px;" type="text" v-model="this.result.lotno">
            
        <label>Quantity</label>
        <input style="margin-bottom: 10px; padding: 10px;" type="number" v-model="this.result.qty">
        
        <label>Production Date</label>
        <input style="margin-bottom: 10px; padding: 10px;" type="date"  v-model="this.result.proddate">
        
        <label>End Date</label>
        <input style="margin-bottom: 10px; padding: 10px;" type="date"  v-model="this.result.enddate">


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
           prodname: '',
           lotno: '',
           qty: '',
           proddate: '',
           enddate: ''
       },
      // username: ''
       }
     },
   created(){
      console.log(this.$store.state.invItem)
      this.result = this.$store.state.invItem
     // this.username = this.$store.state.SessionUser
   },
   methods:{
      update(){
           axios.post("/api/update", {
            prodcode: this.$store.state.invItem.prodcode,
            prodname: this.$store.state.invItem.prodname,
            lotno: this.result.lotno,
            qty: this.result.qty,
            proddate: this.result.proddate+"",
            enddate: this.result.enddate+""
         })
            .then((response) => (response.data = this.$store.state.invItem))
            console.log(this.$store.state.invItem)
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
   
   .form-container input[type=text], .form-container input[type=number], .form-container input[type=date]{
     width: 92%;
     padding: 15px;
     margin: 5px 0 22px 0;
     border: none;
     background: #f1f1f1;
   }
   
   .form-container input[type=text]:focus, .form-container input[type=number]:focus, .form-container input[type=date]:focus {
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