import React, { useState } from 'react';
import { Button, Container, Form, Nav, Navbar } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import Session from 'react-session-api';

  const FormHeader = (props) => {

    const currentuser = sessionStorage.getItem("id");
    let navigate = useNavigate(); 

    const baseURL = "http://localhost:8080/api/";


    const logout = async () => {
     // await axios.get(baseURL + "logout");
      Session.clear();
      navigate('/');
    }

    return (
        <Navbar bg="dark" data-bs-theme="dark" style={{marginBottom:"15px"}}>
        <Container>
          <Link to="/home" className="navbar-brand">Home</Link>
          <Nav className="me-auto"> 
          <Button className="nav-link" onClick={() => logout()}>Logout</Button><span> {currentuser}</span>
          </Nav>
   
        </Container>
      </Navbar>
     

    );
};

export default FormHeader;