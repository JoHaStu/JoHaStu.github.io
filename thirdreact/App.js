
import './App.css';
import { Route } from 'react-router';
import { Routes } from 'react-router';
import React  from 'react';
import Login from './views/Login';
import Register from './views/Register';
import Insert from './views/Insert';
import SessionHome from './views/SessionHome';
import Update from './views/Update';

function App() {
  return (
<div>
<Routes>
  <Route path="/" exact={true} element={<Login />}/>
  <Route path="/registerForm" exact={true} element={<Register />}/>
  <Route path="/home" exact={true} element={<SessionHome />}/>
  <Route path="/insertForm" exact={true} element={<Insert />}/>
  <Route path="/updateForm/:prodcode" exact={true} element={<Update />}/>
  <Route />
</Routes>
</div>
  );
}

export default App;
