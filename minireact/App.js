import './App.css';
import React, {useState} from 'react';
import {Container} from 'react-bootstrap';
import { Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import InsertForm from './views/InsertForm';
import Home from './views/Home';
import UpdateForm from './views/UpdateForm';
import DtlUpdateForm from './views/DtlUpdateForm';
import DtlInsertForm from './views/DtlInsertForm';
import Combined from './views/Combined';
import axios from 'axios';

function App(props) {
//const [items, setItems] = useState([]);
const baseURL = "http://localhost:8080/api/";

//const [mstsearchword, setMstsearchword] = useState([]);

const [searchResult, setSearchResult] = useState([]);

const searchMst = async (mstsearchword) => {
  //setMstsearchword(mstsearchword);
  console.log(mstsearchword);
  const response = await axios.get(baseURL + "searchMst?searchword=" + mstsearchword)
  setSearchResult(response.data);
  console.log(response.data);
  console.log(searchResult);
  // .then((response) => response.data)
  // .then((response) => {setItems(response.data)}, console.log(items));
 // .then((res) => {const response = res.data;
  // setItems(response); 
  // console.log(response);
  // console.log(items)})
  // .then((res) => {setItems(res); 
  // console.log(items)});
 
}



  return (
    <div>
    <Header searchMst={searchMst} />
    {/* <Header searchItems={searchItems} /> */}
    <Container>
       <Routes>
        {/* <Route path="/home" exact={true} element={<Home searchresult={props.searchresult}/>} /> */}
        <Route path="/home" exact={true} element={<Home searchResult={searchResult}/>} />
        <Route path="/insertForm" exact={true} element={<InsertForm />} />
        <Route path="dtlInsertForm/:mst_cd" exact={true} element={<DtlInsertForm />} />
        <Route path="/updateForm/:mst_cd" exact={true} element={<UpdateForm />} />
        <Route path="/dtlUpdateForm/:mst_cd/:dtl_cd" exact={true} element={<DtlUpdateForm />} />
        <Route path="/combined" exact={true} element={<Combined />} />
     </Routes>  
    </Container>
    </div>
  );
}

export default App;
