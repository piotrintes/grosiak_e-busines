import React, {Component} from 'react';

class ProductAdd extends Component
{
    constructor() {
        super();
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleSubmit(event) {
        event.preventDefault();
        const data = new FormData(event.target);
        var url = 'http://localhost:9000/addproduct';
        fetch(url, {
            method: 'POST',
            body: data,
        });
    }
    render() {
        return (
            <div>
                <h1 style={{"textAlign": "center"}}>Dodaj produkt</h1>
                <form onSubmit={this.handleSubmit}>
                    <input id="type" name="type" type="text" defaultValue={"product"} style={{"display": "None"}} />
                    <table className="table_horizontal_style">
                        <tbody>
                        <tr>
                            <td>
                                <label htmlFor="name">Nazwa produktu</label>
                            </td>
                            <td>
                                <input id="name" name="name" type="text" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label htmlFor="category">Katekgoria</label>
                            </td>
                            <td>
                                <input id="category" name="category" type="text" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label htmlFor="quantity">Ilość</label>
                            </td>
                            <td>
                                <input id="quantity" name="quantity" type="text" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label htmlFor="price">Cena za sztukę</label>
                            </td>
                            <td>
                                <input id="price" name="price" type="text" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label htmlFor="imageFilename">Zdjęcie</label>
                            </td>
                            <td>
                                <input id="imageFilename" name="imageFilename" type="text" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label htmlFor="descriptionFilename">Opis</label>
                            </td>
                            <td>
                                <input id="descriptionFilename" name="descriptionFilename" type="text" />
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div style={{"textAlign": "center"}}>
                        <input type="submit" value="Dodaj" />
                    </div>
                </form>
            </div>
        );
    }
}

export default ProductAdd;