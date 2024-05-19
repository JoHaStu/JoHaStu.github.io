import { Button, Container, Form, Navbar } from "react-bootstrap";
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const CombinedSearchbar = (props) => {

    const baseURL = "http://localhost:8080/api/";
    
    const [allSearchResult, setAllSearchResult] = useState([]);

    const [searchword, setAllSearchword] = useState({
        allsearchword: ""
      });
    const [searchoptions, setSearchoptions] = useState({
        searchoptions: ""
    });
    
      const changeValue = (e) => {
        setAllSearchword({
            ...searchword,
            [e.target.name]:e.target.value
        });
    }

    const changeOption = (e) => {
        setSearchoptions({
            ...searchoptions,
            [e.target.name]:e.target.value
        });
    }


    const searchAll =  async (allsearchword) => {

        // setAll('');
 
         console.log(allsearchword);
         const response = 
         await axios.get(baseURL + "searchAll?searchword=" + searchword.allsearchword + "&searchoptions=" + searchoptions.searchoptions);
         //.then((response) => response.data)
        // .then((response) => {setAll(response)}, console.log(all));
        setAllSearchResult(response.data);
        console.log(response.data);
        console.log(allSearchResult);
       
    }


return (
    <div>
<Navbar bg="dark" data-bs-theme="dark" style={{marginBottom:"15px"}}>
        <Container>
        <Form className="d-flex">
        <Form.Select aria-label="Default select example" name="searchoptions" onChange={changeOption}>
        <option>Select a search category</option>
        <option value="1">Master Name</option>
        <option value="2">Master Notes</option>
        <option value="3">Detail Name</option>
        <option value="3">Detail Notes</option>
        </Form.Select>
       {'  '}
              <Form.Control type="search" 
              placeholder="Enter Searchterm" 
              className="me-2" 
              aria-label="Search" 
              name="allsearchword" 
              onChange={changeValue}
              />
              <Button variant="outline-success" onClick={() => searchAll(searchword)}>Search</Button>
        </Form>
        </Container>
      </Navbar>
      </div>
)
}

export default CombinedSearchbar;