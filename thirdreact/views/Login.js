import React from "react";
import LoginBar from "../components/LoginBar";
import {Container, Form, Button} from 'react-bootstrap';
import axios from 'axios';
import Session from 'react-session-api';
import { useState, useEffect } from 'react';
import { useParams, useNavigate } from "react-router-dom";

const Login = () => {

    const baseURL = "http://localhost:8080/api/";

    const [user, setUser] = useState({
       enterid: "",
       enterpw: "",
       //test_result: "",
       error: Boolean
   });
   const {enterid, enterpw} = user;
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
  axios.post(baseURL + "login", {
    id: user.enterid,
    pw: user.enterpw,
   // test_result: user.test_result
  })
  .then((res) => { const response = res.data;
    console.log("response =======>" + res.data);
    if(response.test_result === 'success'){
        let currentuser = user.enterid;
        Session.set("currentuser", currentuser);
        navigate('/home');
    } else{
        document.getElementById('error').style.visibility = 'visible';
      }})
   }

    return (
        <div>
          <LoginBar />
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

export default Login;