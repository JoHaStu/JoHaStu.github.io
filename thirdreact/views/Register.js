import React, {useState} from 'react';
import LoginBar from '../components/LoginBar';
import {Container, Form, Button} from 'react-bootstrap';
import axios from 'axios';
import { useNavigate } from "react-router-dom";

const Register = () => {

  const baseURL = "http://localhost:8080/api/";

const [newuser, setNewUser] = useState({
    id: "",
    pw: "",
    cpw: "",
    error1: Boolean,
    error2: Boolean
});
const { id, pw, cpw}=newuser;

const changeValue = (e) => {
    setNewUser({
        ...newuser,
        [e.target.name]:e.target.value
    });
}

let navigate = useNavigate(); 

const submitRegister = async (e) => {
     e.preventDefault();
     await axios.post(baseURL + "registerUser", {
      id: newuser.id,
      pw: newuser.pw,
      cpw: newuser.cpw})
    .then((res) => { const response = res.data;
      console.log(res);
      if(response.test_result === 'success'){
          console.log("res====>", response);
          navigate('/');
          alert("User successfully added");
      } else if (response.test_result === 'usertaken'){
        console.log("res===user taken===>", response);
        document.getElementById('error1').style.visibility = 'visible';
        }
      else{
        console.log("res===pw dont match===>", response);
        document.getElementById('error2').style.visibility = 'visible';
      }})
     }

    return(
        <div>
          <LoginBar />
          <Container>
                <h2>Sign up</h2>
                
            <Form onSubmit={(e)=>submitRegister(e)}>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Username</Form.Label>
              <Form.Control type="text" placeholder="Choose a Username" onChange={(e)=>changeValue(e)} name="id" value={id}/>
            </Form.Group>
            <Form.Text id="error1" style={{color: "red" , visibility: 'hidden'}}>
                This username is taken
            </Form.Text>

            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Password</Form.Label>
              <Form.Control type="password" placeholder="Enter Password" onChange={(e)=>changeValue(e)} name="pw" value={pw} />
            
            <Form.Group className="mb-3" controlId="formBasicEmail"></Form.Group> 
            <Form.Label>Confirm Password</Form.Label>
              <Form.Control type="password" placeholder="Enter Password Again" onChange={(e)=>changeValue(e)} name="cpw" value={cpw} />
              <Form.Text id="error2" style={{color: "red" , visibility: 'hidden'}}>
                The passwords don't match
              </Form.Text>
            </Form.Group>
      
            <Button variant="dark" type="submit">
             Register
            </Button>
        </Form>
            </Container> 
        </div>

    );


}

export default Register;