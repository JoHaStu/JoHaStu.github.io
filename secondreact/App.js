import './App.css';
import React, { useState } from 'react';
import {Container} from 'react-bootstrap';
import { Routes, Route } from 'react-router-dom';
 import Register from './views/Register';
 import InsertForm from './views/InsertForm';
 import Home from './views/Home';
 import UpdateForm from './views/UpdateForm';
 import CommentInsert from './views/CommentInsert';
import Login from './views/Login';
 import CommentUpdate from './views/CommentUpdate';
 import DetailView from './views/DetailView';
 import DtlViewUpdateForm from './views/DtlViewUpdateForm';


function App() {

   const [currentuser, setCurrentuser] = useState("");

  return (
<div>
  
      <Routes>
         <Route path="/" exact={true}  element={<Login />} /> 
        <Route path="/register" exact={true} element={<Register />} />
        <Route path="/home" exact={true} element={<Home />} />
        <Route path="/insertForm" exact={true} element={<InsertForm />} /> 
        <Route path="/commentInsert/:boardid" exact={true} element={<CommentInsert />} />
        <Route path="/updateForm/:boardid" exact={true} element={<UpdateForm />} />
        <Route path="/commentsUpdate/:boardid/:commentsid" exact={true} element={<CommentUpdate />} />
        <Route path="/detailView/:boardid" exact={true} element={<DetailView />} /> 
        <Route path="/updateForm/:boardid" exact={true} element={<UpdateForm />} />
        <Route path="/dtlViewUpdateForm/:boardid" exact={true} element={<DtlViewUpdateForm />} />
      </Routes>
      </div>
  );
}

export default App;