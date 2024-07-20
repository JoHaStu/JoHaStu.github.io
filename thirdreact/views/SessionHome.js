import React, { useState, useEffect } from 'react';
import SessionBar from '../components/SessionBar';
import { Table, Container, Button } from 'react-bootstrap';
import axios from 'axios';
import Session from 'react-session-api';
import { Link } from 'react-router-dom';

const SessionHome = (props) => {

    let currentuser = Session.get("currentuser");

    const baseURL = "http://localhost:8080/api/";

    const [items, setItems] = useState([]); 

    useEffect(() => {
        axios.get(baseURL + "list")
    .then((response) => response.data)
    .then((response) => {setItems(response)});
}, []) //empty array added to stop useEffect to keep endlessly rendering
    
    const deleteProduct = async (prodcode) => {
        await axios.post(baseURL + "delete", {
            prodcode: prodcode,
        })
        .then((response) => response.data)
        .then((response) => {setItems(response)})
    }

  // const [searchResult, setSearchResult] = useState([]);

    const searchInventory = async (paramword) => {
        console.log(paramword);
       const response = await axios.get(baseURL + "searchInv?paramword=" + paramword)
        setItems(response.data)
       //  setSearchResult(response.data);
       // setItems(searchResult);
      }

return (
    <div>
        <SessionBar searchInventory={searchInventory} />
       <Container>
        <Table striped bordered hover>
            <thead>
                <tr>
                <th>Product Code</th>
             <th>Product Name</th>
             <th>Lot Number</th>
             <th>Quantity</th>
             <th>Production Date</th>
             <th>End Date</th>
             <th>Update</th>
             <th>Delete</th>
                </tr>
            </thead>
            <tbody>
            {items.map((item) => (<tr key={item.prodcode} item={item}>
             <td>{item.prodcode}</td>  
             <td>{item.prodname}</td>  
             <td>{item.lotno}</td>
             <td>{item.qty}</td>
             <td>{item.proddate}</td>
             <td>{item.enddate}</td>   
             <td><Link to={'/updateForm/' + item.prodcode} className="btn btn-dark" >Update</Link></td>
             <td><Button variant="dark" onClick={() => deleteProduct(item.prodcode)}>Delete</Button></td> 
                </tr>))}
            </tbody>
        </Table>
        </Container>
    </div> 
);


};

export default SessionHome;