/**
 * Created by Paulina on 13.01.2018.
 */
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

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
                </div>
                <div className="row main">
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

module.exports = {
    registerPanel: RegisterPanel
};