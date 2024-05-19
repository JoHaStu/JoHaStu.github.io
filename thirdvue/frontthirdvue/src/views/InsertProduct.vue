<template>
    <div class="page-container">
    
        <HeaderBar />
        <FormBar />

    <div id="NewProduct" class="insertdiv">
	
    <div style="font-size: smaller;" class="tabcontent">

    <!-- <q-btn id="popupbutton" class="buttons" v-onclick="popup()">Choose Product Name & Lot Number</q-btn> -->
    <!-- <button id="popupbutton" class="buttons" @click.prevent="popup()" v-on:selectedProdname="prodnameSelection" v-on:selectedLotno="lotnoSelection">Choose Product Name & Lot Number</button> -->
    <button id="popupbutton" class="buttons" @click.prevent="popup()" >Choose Product Name & Lot Number</button>


    <form @submit.prevent="insert()" method="post" class="form-container" >

     <h2>Add a Product to the Inventory</h2>
 
     <label>Product Name</label>
    <!-- <input style="margin-bottom: 10px; padding: 10px;" type="text" placeholder="Please choose from options above" id="insprodname" name="insprodname" v-model="this.result.prodname"> -->
    <input style="margin-bottom: 10px; padding: 10px;" type="text" placeholder="Please choose from options above" id="insprodname" name="insprodname" >

     <label>Lot Number</label>
    <!-- <input style="margin-bottom: 10px; padding: 10px;" type="text" placeholder="Please choose from options above" id="inslotno" name="inslotno" v-model="this.result.lotno"> -->
     <input style="margin-bottom: 10px; padding: 10px;" type="text" placeholder="Please choose from options above" id="inslotno" name="inslotno" >

         
    <label>Quantity</label>
    <input style="margin-bottom: 10px; padding: 10px;" type="number" placeholder="Enter Quantity" name="insqty" v-model="this.result.qty">
    
    <label>Production Date</label>
    <input style="margin-bottom: 10px; padding: 10px;" type="date" placeholder="Enter Production Date" name="insproddate" v-model="this.result.proddate">
    
    <label>End Date</label>
    <input style="margin-bottom: 10px; padding: 10px;" type="date" placeholder="Enter End Date" name="insenddate" v-model="this.result.enddate">

     <button type="submit" class="btn">Submit</button>
   </form>

   </div>
   </div>

   </div>
</template>

<script>
import axios from 'axios';

import HeaderBar from '../components/HeaderBar.vue';
import FormBar from '../components/FormBar.vue';

export default {
    name: 'InsertProduct',

    components:{
        HeaderBar,
        FormBar
        
    },

 data() {
    return {
      result: {
       // boardid: '',
        prodname: '',
        lotno: '',
        qty: '',
        proddate: '',
        enddate: '',
        
    },
      username: '',
      // prodnamecat: '',
      // lotnocat: ''
    }
},

created(){
    this.username = this.$store.state.SessionUser;

    },

methods:{
    // insert(){
    //     axios.post("/api/insert", {
    //       prodname: this.result.prodname,
    //       lotno: this.result.lotno,
    //       qty: this.result.qty,
    //       proddate: this.result.proddate,
    //       enddate: this.result.enddate,
        
    //   })
    insert(){
        axios.post("/api/insert?prodname=" + document.getElementById("insprodname").value
        + "&lotno=" + document.getElementById("inslotno").value
        + "&qty=" + this.result.qty
        + "&proddate=" + this.result.proddate
        + "&enddate=" + this.result.enddate)

      .then((response) => (response.data = this.$store.state.invItem))
            console.log(this.$store.state.invItem)
            this.$router.push({name: 'SessionHomeView'})
        },

    popup (){
          window.open("/popup","_blank", "width=400, height=500,toolbar=no,scrollbars=no,resizable=yes,status=no,menubar=no,top=0,left=0");
        },

        // setProdname(){
        //   this.result.prodname = this.$store.state.prodname;
        // },

        // setLotno(){
        //   this.result.lotno = this.$store.state.prodname;
        // },

//         prodnameSelection(prodnamecat){
//           this.result.prodname = prodnamecat;
//         },

//         lotnoSelection(lotnocat){
//           this.result.lotno = lotnocat;
//         }
//       },

//       popupClosed(){
//         window.addEventListener('message', (event) => {
//   if (event.data === 'selected') {
//     onPopupClosed();
//   }
// });
// const onPopupClosed = () => {
//   this.result.prodname = prodnamecat;
//   this.result.lotno = lotnocat;
//   console.log('Popup closed');
// };
},

// mounted(){

// window.addEventListener(
//   "popupClosed",
//   (chooseCategories) => {
//     if (chooseCategories.origin !== "/popup") return;
//     this.result.prodname = selectedProdname;
//     this.result.lotno = selectedLotno;
//   },
//   false,
// );
//     }
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

.insertdiv {
  position:absolute;
  z-index: 9999;
  /*border: 3px solid #f1f1f1;*/
  width: 40%;
  margin-top: 5px;
  margin-bottom: 30px;
}

.buttons {
    background-color: #066d06;
    font-size: smaller;
    color: white;
    padding: 5px;
    border: none;
    cursor: pointer;
    margin-left: 5px;
    border-radius: 5%;
    }

</style>