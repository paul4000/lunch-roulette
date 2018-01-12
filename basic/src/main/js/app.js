'use strict';

// tag::vars[]
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
import {BrowserRouter, Switch, Route} from 'react-router-dom';

// end::vars[]

// tag::app[]
const App = () => (
    <Switch>
        <Route exact path='/' component={RegisterPanel}/>
        <Route path='/home' component={Home}/>
    </Switch>
);

// end::app[]

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
            <div className="form-group ">
                <button type="button" className="btn btn-primary btn-lg btn-block logout-button" onClick={this.handleSubmit}>Log out</button>
            </div>
        )
    }
}

class Home extends React.Component{
    render(){
        return (
            <div>
                <Header/>
                <div>test home</div>
            </div>
        );
    }
}

class LoginPanel extends React.Component{
    render(){
        return (
            <div>test login</div>
        )
    }
}

class RegisterPanel extends React.Component{

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        var newEmployee = {};
        // this.props.attributes.forEach(attribute => {
        //     newEmployee[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
        // });
        newEmployee["name"] = ReactDOM.findDOMNode(this.refs["name"]).value.trim();
        newEmployee["email"] = ReactDOM.findDOMNode(this.refs["email"]).value.trim();
        newEmployee["username"] = ReactDOM.findDOMNode(this.refs["username"]).value.trim();
        newEmployee["password"] = ReactDOM.findDOMNode(this.refs["password"]).value.trim();

        client({
            method: 'POST',
            path: '/users/register',
            entity: newEmployee,
            headers: {'Content-Type': 'application/json'}
        }).done(response => {
            console.log(response);
            if (response.status.code === 200) {
                this.props.history.push('/home');
            }
        });

        console.log(newEmployee);

        // this.props.onCreate(newEmployee);

        // clear out the dialog's inputs
        // this.props.attributes.forEach(attribute => {
        //     ReactDOM.findDOMNode(this.refs[attribute]).value = '';
        // });

        // Navigate away from the dialog to hide it.
        // window.location = "#";
    }


    render() {

        return (
            <div className="container">
                <div className="row main">
                    <div className="panel-heading">
                        <div className="panel-title text-center">
                            <h1 className="title">Lunch Roulette</h1>
                            <hr />
                        </div>
                    </div>
                    <div className="main-login main-center">
                        <form className="form-horizontal" method="post" action="#">

                            <div className="form-group">
                                <label htmlFor="name" className="cols-sm-2 control-label">Your Name</label>
                                <div className="cols-sm-10">
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-user fa" aria-hidden="true"></i></span>
                                        <input type="text" className="form-control" name="name" ref="name"  placeholder="Enter your Name"/>
                                    </div>
                                </div>
                            </div>

                            <div className="form-group">
                                <label htmlFor="email" className="cols-sm-2 control-label">Your Email</label>
                                <div className="cols-sm-10">
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-envelope fa" aria-hidden="true"></i></span>
                                        <input type="text" className="form-control" name="email" ref="email"  placeholder="Enter your Email"/>
                                    </div>
                                </div>
                            </div>

                            <div className="form-group">
                                <label htmlFor="username" className="cols-sm-2 control-label">Username</label>
                                <div className="cols-sm-10">
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-users fa" aria-hidden="true"></i></span>
                                        <input type="text" className="form-control" name="username" ref="username"  placeholder="Enter your Username"/>
                                    </div>
                                </div>
                            </div>

                            <div className="form-group">
                                <label htmlFor="password" className="cols-sm-2 control-label">Password</label>
                                <div className="cols-sm-10">
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                        <input type="password" className="form-control" name="password" ref="password"  placeholder="Enter your Password"/>
                                    </div>
                                </div>
                            </div>

                            <div className="form-group">
                                <label htmlFor="confirm" className="cols-sm-2 control-label">Confirm Password</label>
                                <div className="cols-sm-10">
                                    <div className="input-group">
                                        <span className="input-group-addon"><i className="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                        <input type="password" className="form-control" name="confirm" ref="confirm"  placeholder="Confirm your Password"/>
                                    </div>
                                </div>
                            </div>

                            <div className="form-group ">
                                <button type="button" className="btn btn-primary btn-lg btn-block login-button" onClick={this.handleSubmit}>Register</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

// tag::employee-list[]
class EmployeeList extends React.Component{
    render() {
        var employees = this.props.employees.map(employee =>
            <Employee key={employee._links.self.href} employee={employee}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Description</th>
                </tr>
                {employees}
                </tbody>
            </table>
        )
    }
}
// end::employee-list[]

// tag::employee[]
class Employee extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.employee.firstName}</td>
                <td>{this.props.employee.lastName}</td>
                <td>{this.props.employee.description}</td>
            </tr>
        )
    }
}
// end::employee[]

// tag::render[]
ReactDOM.render((
    <BrowserRouter>
        <App />
    </BrowserRouter>
), document.getElementById('react'))
// end::render[]

