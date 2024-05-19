import React, { useState } from 'react';
import { Button, Container, Form, Nav, Navbar } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import Session from 'react-session-api';

  const SessionHeader = (props, {searchBoard}) => {

    let currentuser = Session.get("currentuser");
    let navigate = useNavigate(); 

    const baseURL = "http://localhost:8080/api/";

    const [searchword, setSearchword] = useState({
        boardsearchword: ""
      });
    
      const changeValue = (e) => {
        setSearchword({
            ...searchword,
            [e.target.name]:e.target.value
        });
    }

    const logout = async () => {
      //await axios.get(baseURL + "logout");
      Session.clear();
      navigate('/');
    }

  let search = searchword.boardsearchword;

    return (
        <Navbar bg="dark" data-bs-theme="dark" style={{marginBottom:"15px"}}>
        <Container>
          <Link to="/home" className="navbar-brand">Home</Link>
          <Nav className="me-auto"> 
          <Link to="/insertForm" className="nav-link">Add a Post</Link>
          {/* <Link to="/logout" className="nav-link">Logout {currentuser}</Link> */}
          <Button class="btn btn-dark" className="nav-link" onClick={() => logout()}>Logout {currentuser}</Button>
          </Nav>
          <Form className="d-flex">
              <Form.Control
                type="search"
                placeholder="Search Post by Title"
                className="me-2"
                aria-label="Search"
                name="boardsearchword"
                onChange={changeValue}
              />
              <Button variant="outline-success" onClick={() => props.searchBoard(search)}>Search</Button>            
           </Form>
        </Container>
      </Navbar>
     

    );
};

export default SessionHeader;