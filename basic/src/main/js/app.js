'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const RegisterPanel = require('./RegisterPanel').registerPanel;
const LoginPanel = require('./LoginPanel').loginPanel;
const UploadRecipe = require('./UploadRecipe').uploadRecipe;
const Recipes = require('./Recipes').recipes;

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
                        <button type="button" className="btn btn-primary" onClick={this.handleSubmit}>Log out</button>
                        <button type="button" className="btn btn-primary"><Link to="/upload" style={{color: '#fff'}}>Add recipe</Link></button>
                        <button type="button" className="btn btn-primary"><Link to="/showRecipes" style={{color: '#fff'}}>Show recipes</Link></button>
                    </div>
                </div>
            </nav>

        )
    }
}

class View1 extends React.Component{
    render(){
        return (
            <div>
                <div>test view 1</div>
            </div>
        );
    }
}
class View2 extends React.Component{
    render(){
        return (
            <div>
                <div>test view 2</div>
            </div>
        );
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

// tag::employee-list[]
// class EmployeeList extends React.Component{
//     render() {
//         var employees = this.props.employees.map(employee =>
//             <Employee key={employee._links.self.href} employee={employee}/>
//         );
//         return (
//             <table>
//                 <tbody>
//                 <tr>
//                     <th>First Name</th>
//                     <th>Last Name</th>
//                     <th>Description</th>
//                 </tr>
//                 {employees}
//                 </tbody>
//             </table>
//         )
//     }
// }
// end::employee-list[]

// tag::employee[]
// class Employee extends React.Component{
//     render() {
//         return (
//             <tr>
//                 <td>{this.props.employee.firstName}</td>
//                 <td>{this.props.employee.lastName}</td>
//                 <td>{this.props.employee.description}</td>
//             </tr>
//         )
//     }
// }
// end::employee[]

// tag::render[]
ReactDOM.render((
    <BrowserRouter>
        <App />
    </BrowserRouter>
), document.getElementById('react'))
// end::render[]

