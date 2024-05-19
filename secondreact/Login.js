
import React, { useState, useEffect } from 'react';
import LoginHeader from '../components/LoginHeader';
import axios from 'axios';
import { useParams, useNavigate } from "react-router-dom";
import { Button, Container, Form } from 'react-bootstrap';
import Session from 'react-session-api';

 
function App() {

//const bcrypt = require('bcrypt');
//const saltRounds = 10;


const baseURL = "http://localhost:8080/api/";

const [user, setUser] = useState({
   // boardid: "",
    enterid: "",
    enterpw: "",
    error: Boolean
});
const {enterid, enterpw}=user;
const [currentuser, setCurrentuser] = useState("");
const [isLoggedin, setIsLoggedin] = useState(false);

const changeValue = (e) => {
    setUser({
        ...user,
        [e.target.name]:e.target.value
    });
}

let navigate = useNavigate(); 

const submitLogin = (e) => {
    e.preventDefault();
    axios.post(baseURL + "login?enterid=" + user.enterid + "&enterpw=" + user.enterpw)
    .then((res) => { const response = res.data;
      console.log(res);
      if(response === 'success'){
          console.log("res====>", response);
          let currentuser = user.enterid;
          Session.set("currentuser", currentuser);
        //   setCurrentuser(enterid);
        //   console.log(currentuser);
        //   sessionStorage.setItem("id", enterid);
        //   setIsLoggedin(true);
        //   sessionStorage.setItem("id", currentuser);
          navigate('/home');
      } else{
          console.log("res===codetaken===>", response);
          //item.error = true;
          document.getElementById('error').style.visibility = 'visible';
        }})
     }



    return (
      
        <div>    
        <LoginHeader />
        <Container>
             <h2>Login</h2>
        <Form onSubmit={submitLogin}>
        <Form.Group className="mb-3" >
          <Form.Label>Username</Form.Label>
          <Form.Control type="text" placeholder="Enter Username" onChange={(e)=>changeValue(e)} name="enterid" value={enterid}/>
        </Form.Group>

        <Form.Group className="mb-3" >
          <Form.Label>Password</Form.Label>
          <Form.Control type="password" placeholder="Enter Password" onChange={(e)=>changeValue(e)} name="enterpw" value={enterpw}/>
          {/* <Form.Control type="text" placeholder="Enter Title"  name="title" ref="title"/> */}
          <Form.Text id="error" style={{color: "red" , visibility: 'hidden'}}>
            The username and password don't match
            </Form.Text>
        </Form.Group>
  
        <Button variant="dark" type="submit">
         Login
        </Button>
    </Form>
        </Container>
    </div>
       
    );
}
 
export default App;