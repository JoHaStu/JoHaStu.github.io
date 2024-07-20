import React from "react";
import {Navbar, Container, Nav, Form, Button} from 'react-bootstrap';
import {Link, useNavigate} from 'react-router-dom';
import Session from 'react-session-api';

const FormBar = () => {

  let currentuser = Session.get("currentuser");
  let navigate = useNavigate();

  const logout = async () => {
    Session.clear();
    navigate('/');
  }

    return (

        <Navbar bg="dark" data-bs-theme="dark" style={{marginBottom:"15px"}}>
        <Container>
          <Link to="/home" className="navbar-brand">Main</Link>
          
          <button type="button" class="btn btn-dark" onClick={() => logout()}>Logout {currentuser}</button>
        </Container>
        
      </Navbar>

    )

}

export default FormBar;