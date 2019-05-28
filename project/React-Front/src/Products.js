import React, {Component} from 'react';

class Products extends Component
{
    constructor() {
        super();
        this.state = {
            products: [],
        };
    }

    componentDidMount() {
        var url = "http://localhost:9000/products"
        fetch(url, {
            mode: 'cors',
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin':'http://localhost:3000',
            },
            method: 'GET',
        })
            .then(results => {
                return results.json();
            }).then(data => {
            let products = data.map((item) => {
                if(item.exists){
                return (
                    <tr key={item.productID}>
                        <td>{item.name}</td>
                        <td>{item.category}</td>
                        <td>{item.quantity}</td>
                        <td>{item.price}</td>
                        <td><img src={item.imageFilename}/></td>
                        <td>{item.descriptionFilename}</td>
                    </tr>
                )} else {
                return
                }
            })
            this.setState({products: products})
        })
    }
    render() {
        return (
            <div className="products">
                <table>
                    <thead>
                    <tr>
                        <th>Produkt</th>
                        <th>Kategoria</th>
                        <th>Ilość</th>
                        <th>Cena za sztukę</th>
                        <th>Zdjęcie</th>
                        <th>Opis</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.state.books}
                    </tbody>
                </table>
            </div>
        )
    }
}

export default Products;