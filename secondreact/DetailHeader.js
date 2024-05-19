import React, { useState } from 'react';
import { Button, Container, Form, Nav, Navbar } from 'react-bootstrap';
import { Link, useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import Session from 'react-session-api';

const DetailHeader = (props, {searchComments}) => {


  const {boardid}  = useParams();

  let navigate = useNavigate(); 

   let currentuser = Session.get("currentuser");

    const [commsearchword, setCommSearchword] = useState({
        commentssearchword: ""
      });
    
      const changeValue = (e) => {
        setCommSearchword({
            ...commsearchword,
            [e.target.name]:e.target.value
        });
    }

    const logout = async () => {
      //await axios.get(baseURL + "logout");
      Session.clear();
      navigate('/');
    }
    
    let commsearch = commsearchword.commentssearchword;
    
    return (
        <Navbar bg="dark" data-bs-theme="dark" style={{marginBottom:"15px"}}>
        <Container>
          <Link to="/home" className="navbar-brand">Home</Link>
          <Nav className="me-auto">
          <Link to={'/commentInsert/'  + boardid} className="nav-link">Add a Comment</Link>
          <Button class="btn btn-dark" className="nav-link" onClick={() => logout()}>Logout {currentuser}</Button>
          </Nav>
          <Form className="d-flex">
              <Form.Control
                type="search"
                placeholder="Search Comments"
                className="me-2"
                aria-label="Search"
                name="commentssearchword"
                onChange={changeValue}
              />
              <Button variant="outline-success" onClick={() => props.searchComments(commsearch)}>Search</Button>
            </Form>
           
        </Container>
      </Navbar>
     

    );
};

export default DetailHeader;