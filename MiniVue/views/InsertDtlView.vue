<template>
    <div class="page-container">
    <div class="header">
       <h2 style="color:white;">Mini Vue Project</h2>
    </div>

    <div id="NewProduct" class="insertdiv">
	
    <div id="dtlinsertFormDiv" style="font-size: smaller;" class="tabcontent">
    <form id="dtlinsertForm" @submit.prevent="insertDtl()" method="post" class="form-container">

     <h2>Add an Item</h2>

     <label>Master Code</label>
     <input style="margin-bottom: 10px; padding: 10px;" type="text" placeholder="Enter Code" name="mst_cd" v-model="this.result.mst_cd"> 
     <span name="error1" v-if="this.error1 === true" class="error">That master code doesn't exist</span><br><br>

     <label>Detail Code</label>
     <input style="margin-bottom: 10px; padding: 10px;" type="text" placeholder="Enter Code" name="dtl_cd" v-model="this.result.dtl_cd">
     <span name="error2" v-if="this.error2 === true" class="error">That detail code is already taken</span><br><br>
 
     <label>Name</label>
     <input style="margin-bottom: 10px; padding: 10px;" type="text" placeholder="Enter Name" name="dtl_name" v-model="this.result.dtl_name">
     
     <label>Notes</label>
     <input style="margin-bottom: 10px; padding: 10px;" type="text" placeholder="Enter Notes" name="dtl_notes" v-model="this.result.dtl_notes">
         
     <button type="submit" class="btn">Submit</button>
   </form>

   </div>
   </div>

   </div>
</template>

<script>
import axios from 'axios';

export default {
 data() {
    return {
      result: {
        mst_cd: '',
        dtl_cd: '',
        dtl_name: '',
        dtl_notes: '',
        dtl_result: ''
    },
      error1: false,
      error2: false

    }
},
methods:{
   async insertDtl(){
       const res = await axios.post("/api/insertDtl", {
        mst_cd: this.result.mst_cd,
        dtl_cd: this.result.dtl_cd,
        dtl_name: this.result.dtl_name,
        dtl_notes: this.result.dtl_notes,
        dtl_result: this.result.dtl_result
      });
      console.log("res.data====>", res.data);
        
      if(res.data.dtl_result === "success"){
          console.log("res====>", res.data.dtl_result);
          this.$router.push({name: 'ListView'})
      } 
      else if(res.data.dtl_result === "noMstCode"){
          console.log("res===noMstCode===>", res.data.dtl_result);
          this.error1 = true;
        }
      else{
          console.log("res===codetaken===>", res.data.dtl_result);
          this.error2 = true;
        }
      }
      
        
       // console.log("res===mst_name==>"+res.data.mst_name)
       // console.log("res====mst_notes=>"+res.data.mst_notes)
       // .then((res) => {
        
        }
       
        //)
        //this.$router.push({name: 'ListView'}) 
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

.insertdiv {
  position:absolute;
  z-index: 9999;
  /*border: 3px solid #f1f1f1;*/
  width: 22%;
  margin-top: 20px;
  margin-bottom: 30px;
}

.error{
  
  color: #cf0e0e;
  text-align: center;
  margin-bottom: 10px;
   
}
</style>