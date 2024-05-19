import React, { useState } from 'react';
import { Button, Container, Form, Nav, Navbar } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import axios from 'axios';

const LoginHeader = (props) => {


    return (
        <Navbar bg="dark" data-bs-theme="dark" style={{marginBottom:"15px"}}>
        <Container>
          <Link to="/" className="navbar-brand">Main</Link>
          <Nav className="me-auto">
          <Link to="/" className="nav-link">Login</Link>
          <Link to="/register" className="nav-link">Sign Up</Link>
          </Nav>           
        </Container>
      </Navbar>
     

    );
};

export default LoginHeader;
