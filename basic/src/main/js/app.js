'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const RegisterPanel = require('./RegisterPanel').registerPanel;
const LoginPanel = require('./LoginPanel').loginPanel;
const UploadRecipe = require('./UploadRecipe').uploadRecipe;
const Recipes = require('./Recipes').recipes;
const Random = require('./Random').random;

import {BrowserRouter, Switch, Route, Link} from 'react-router-dom';

import Alert from 'react-s-alert';

const App = () => (
    <Switch>
        <Route exact path='/' component={RegisterPanel}/>
        <Route path='/login' component={LoginPanel}/>
        <Route path='/home' component={Home}>
        </Route>
        <Home>
            <Switch>
                <Route path='/upload' component={UploadRecipe}/>
                <Route path='/showRecipes' component={Recipes}/>
                <Route path='/random' component={Random}/>
            </Switch>
        </Home>
    </Switch>
);

class Header extends React.Component{

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);

    }

    handleSubmit(e) {
        client({
            method: 'GET',
            path: '/logout',
            headers: {'Content-Type': 'application/json'}
        }).done(response => {
            console.log(response);
            window.location.href = "/";
        });
    }

    render(){
        return (
            <nav className="navbar navbar-inverse">
                <div className="container-fluid">
                    <div className="navbar-header">
                        <a className="navbar-brand" href="#">Lunch Roulette</a>
                    </div>
                    <div className="form-group">
                        <button type="button" className="btn btn-primary" onClick={this.handleSubmit}>Wyloguj</button>
                        <button type="button" className="btn btn-primary"><Link to="/upload" style={{color: '#fff'}}>Dodaj przepis</Link></button>
                        <button type="button" className="btn btn-primary"><Link to="/showRecipes" style={{color: '#fff'}}>Moje przepisy</Link></button>
                        <button type="button" className="btn btn-primary"><Link to="/random" style={{color: '#fff'}}>Wylosuj</Link></button>
                    </div>
                </div>
            </nav>

        )
    }
}

const Home = (props) => {
        return (
            <div>
                <Header/>
                {props.children}
                <Alert stack={{limit: 3}} />
            </div>
        );
};

ReactDOM.render((
    <BrowserRouter>
        <App />
    </BrowserRouter>
), document.getElementById('react'))
