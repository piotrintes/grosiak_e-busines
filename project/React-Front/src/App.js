import React, {Component} from 'react';
import {
  BrowserRouter as Router,
  Route,
  Link
} from 'react-router-dom';
import Products from './Products';
import ProductAdd from './ProductAdd';

class App extends Component
{
  render()
  {
    return <Router>
      <div id="menu">
        <ul>
          <li>
            <Link to="/products">Produkty</Link>
          </li>
          <li>
            <Link to="/add_product">Dodaj produkt</Link>
          </li>
        </ul>
        <Route path="/products" component={Products}/>
        <Route path="/add_product" component={ProductAdd}/>
      </div>
    </Router>
  }
}

export default App;
