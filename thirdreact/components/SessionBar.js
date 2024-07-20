import React, {useState} from "react";
import {Navbar, Container, Nav, Form, Button} from 'react-bootstrap';
import {Link, useNavigate} from 'react-router-dom';
import Session from 'react-session-api';

const SessionBar = (props, {searchInventory}) => {

  let currentuser = Session.get("currentuser");
  let navigate = useNavigate();

  const logout = async () => {
    Session.clear();
    navigate('/');
  }

  const baseURL = "http://localhost:8080/api/";

    const [searchword, setSearchword] = useState({
        invsearchword: ""
      });
    
      const changeValue = (e) => {
        setSearchword({
            ...searchword,
            [e.target.name]:e.target.value
        });
    }

    let search = searchword.invsearchword;
  
  

    return (

        <Navbar bg="dark" data-bs-theme="dark" style={{marginBottom:"15px"}}>
        <Container>
         <Link to="/home" className="navbar-brand">Main</Link>
          <Nav className="me-auto"> 
          <Link to="/insertForm" className="nav-link">Add item</Link> 
          <Form className="d-flex">
              <Form.Control
                type="search"
                placeholder="Enter a search term"
                className="me-2"
                aria-label="Search"
                name="invsearchword"
                onChange={changeValue}
              />
              <Button variant="outline-success" onClick={() => props.searchInventory(search)}>Search</Button>            
           </Form>
          </Nav>
          <button type="button" class="btn btn-dark" onClick={() => logout()}>Logout {currentuser}</button>
        </Container>
        
      </Navbar>

    )

}

export default SessionBar;