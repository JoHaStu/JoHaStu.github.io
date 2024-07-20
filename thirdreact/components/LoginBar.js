import React from 'react';
import {Navbar, Container, Nav} from 'react-bootstrap';
import {Link} from 'react-router-dom';

const LoginBar = () => {

    return (

        <Navbar bg="dark" data-bs-theme="dark" style={{marginBottom:"15px"}}>
        <Container>
          <Link to="/" className="navbar-brand" disabled>Main</Link>
          <Nav className="me-auto">
          <Link to="/" className="nav-link">Login</Link>
          <Link to="/registerForm" className="nav-link">Sign Up</Link>
          </Nav>           
        </Container>
      </Navbar>

    )

}

export default LoginBar;