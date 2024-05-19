import { Button, Card, Container, Form, Navbar, Table } from "react-bootstrap";
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useNavigate } from "react-router-dom";
//import DetailTable from "./DetailTable";
//import { useNavigate } from "react-router-dom";


const Combined = (props) => {
   
    const [all, setAll] = useState([]);

   // const [allresult, setAllResult] = useState([]);

    const baseURL = "http://localhost:8080/api/";
    
    useEffect(() => {
        
    
        axios.get(baseURL + "getAllCombined")
        .then((response) => response.data)
        .then((response) => {
  
            setAll(response)

        }, console.log(all));
    }, []) 


    // useEffect(() => {
    //     setAll(props.allSearchResult)
    // }, [props.allSearchResult])

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

      const searchAll =  async (e) => {
       e.preventDefault();
    setAll([]);
       const response = 
       await axios.get(baseURL + "searchAll?searchword=" + searchword.allsearchword + "&searchoptions=" + searchoptions.searchoptions);
    //     //.then((response) => response.data)
    //    // .then((response) => {setAll(response)}, console.log(all));
     // setAllResult(response.data);
      //setAllSearchResult(response)
       console.log(response.data);
     //  console.log(allresult);
       setAll(response.data);
      }
        //setResult(response.data);
        //console.log(response.data);
       
      
   // };
    
// useEffect(() => {
//     setAll(result)
// }, [result])

   return (
    <div>
        <Card style={{backgroundColor: "darkslategray"}}> 
        <Card.Body>
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
              <Button variant="outline-success" onClick={searchAll}>Search</Button>
        </Form>
        </Container>
      </Navbar>
    
    
    <Table striped bordered hover>
    <thead>
         <tr>
         <th>Master Code</th>
         <th>Master Name</th>
         <th>Master Notes</th>
         <th>Master Input Date</th>
         <th>Master Update Date</th>
         <th>Detail Code</th>
         <th>Detail Name</th>
         <th>Detail Notes</th>
         <th>Detail Input Date</th>
         <th>Detail Update Date</th>
         </tr>
       </thead>
       <tbody>
       {all.map((every) => (<tr key={every.mst_cd} every={every} allSearchResult={props.allSearchResult}>
         <td>{every.mst_cd}</td>    
         <td>{every.mst_name}</td>
         <td>{every.mst_notes}</td>
         <td>{every.mst_reg_date}</td>
         <td>{every.mst_upd_date}</td>
         <td>{every.dtl_cd}</td>    
         <td>{every.dtl_name}</td>
         <td>{every.dtl_notes}</td>
         <td>{every.dtl_reg_date}Detail Input Date</td>
         <td>{every.dtl_upd_date}Detail Update Date</td>
         </tr>
         ))}
       </tbody>
       </Table>
</Card.Body>
</Card>
<br/>
</div>

  




    );
};


export default Combined;