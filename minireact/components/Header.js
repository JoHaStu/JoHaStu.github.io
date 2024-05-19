import React, { useState } from 'react';
import { Button, Container, Form, Nav, Navbar } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import axios from 'axios';

const Header = (props, {searchItems}) => {


  const [searchword, setSearchword] = useState({
    mstsearchword: ""
  });
  //const [searchresult, setSearchResult] = useState();

  const changeValue = (e) => {
    setSearchword({
        ...searchword,
        [e.target.name]:e.target.value
    });
}

let search = searchword.mstsearchword;


const baseURL = "http://localhost:8080/api/";

// const searchMst = (e) => {
//   e.preventDefault();
//   axios.get(baseURL + "searchMst?searchword=" + searchword)
//   .then((response) => response.data)
//   .then((response) => {searchItems(response)});
//   //.then((response) => {setSearchResult(response)})
// }


    return (
        <Navbar bg="dark" data-bs-theme="dark" style={{marginBottom:"15px"}}>
        <Container>
            <Link to="/home" className="navbar-brand">Home</Link>
          {/* <Navbar.Brand href="#home">BookPagePractice</Navbar.Brand>  */}
          
          <Nav className="me-auto">
          <Link to="/home" className="nav-link">Home</Link> 
          <Link to="/insertForm" className="nav-link">Add Master Entry</Link>
          <Link to="/combined" className="nav-link">Combined Overview</Link>
          </Nav>
          <Form className="d-flex">
              <Form.Control
                type="search"
                placeholder="Search by Title"
                className="me-2"
                aria-label="Search"
                name="mstsearchword"
                onChange={changeValue}
              />
              <Button variant="outline-success" onClick={() => props.searchMst(search)}>Search</Button>
            </Form>
           
        </Container>
      </Navbar>
     

    );
};

export default Header;