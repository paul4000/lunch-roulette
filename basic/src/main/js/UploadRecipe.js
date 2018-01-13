/**
 * Created by Paulina on 13.01.2018.
 */

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

import Alert from 'react-s-alert';

class UploadRecipe extends React.Component{

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.state = {
            showError: false
        };
    }

    handleSubmit(e) {
        e.preventDefault();
        var newRecipe = {};
        // this.props.attributes.forEach(attribute => {
        //     newEmployee[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
        // });
        newRecipe["name"] = ReactDOM.findDOMNode(this.refs["name"]).value.trim();
        newRecipe["ingredients"] = ReactDOM.findDOMNode(this.refs["ingredients"]).value.trim();
        newRecipe["steps"] = ReactDOM.findDOMNode(this.refs["steps"]).value.trim();

        client({
            method: 'POST',
            path: '/recipes',
            entity: newRecipe,
            headers: {'Content-Type': 'application/json'}
        }).then(response => {
            // console.log(response);
            if (response.status.code === 200) {
                ReactDOM.findDOMNode(this.refs["name"]).value = '';
                ReactDOM.findDOMNode(this.refs["ingredients"]).value = '';
                ReactDOM.findDOMNode(this.refs["steps"]).value = '';

                alert('You added new recipe');
                // Alert.info('You added new recipe', {
                //     position: 'top-center',
                //     effect: 'bouncyflip',
                //     timeout: 'none'
                // });

            }

        })
            .catch(error => {
                this.setState({ showError: true });
            });
        // this.props.attributes.forEach(attribute => {
        //     ReactDOM.findDOMNode(this.refs[attribute]).value = ''; // clear out the dialog's inputs
        // });
        // console.log(newEmployee);
        // // Navigate away from the dialog to hide it.
        // window.location = "#";
    }


    render() {

        return (
            <div className="container">
                <div className="row main">
                    <div className="panel-heading">
                        <div className="panel-title text-center">
                            <h4 className="title">Upload your new recipe</h4>
                        </div>
                    </div>
                </div>

                <div className="row main">
                    <div className="main-login main-upload">
                        { this.state.showError ?
                        <div className="alert alert-dismissible alert-danger">
                            <button type="button" className="close" onClick={() => this.setState({ showError: false }) }></button>
                            <strong>Wrong recipe format!</strong>
                        </div> : null }

                        <form className="form-horizontal" method="post" action="#">

                            <div className="form-group">
                                <label htmlFor="name" className="control-label">Recipe name</label>
                                <div className="cols-sm-10">
                                    <div className="input-group">
                                        <input type="text" className="form-control" name="name" ref="name"  placeholder="Recipe name..."/>
                                    </div>
                                </div>
                            </div>

                            <div className="form-group">
                                <label htmlFor="ingredients" className="control-label">Please provide ingredients list in the following form: <br/>
                                ingredient name:quantity <br/>
                                each ingredient in separate line!</label>
                                <div className="cols-sm-10">
                                    <div className="input-group">
                                        <textarea className="form-control" id="formControlTextarea1" name="ingredients" ref="ingredients" rows="3" placeholder="Give ingredients..."/>
                                    </div>
                                </div>
                            </div>

                            <div className="form-group">
                                <label htmlFor="steps" className="control-label">Please provide steps list in the following form: <br/>
                                sequence number:description of step <br/>
                                new step in new line !</label>
                                <div className="cols-sm-10">
                                    <div className="input-group">
                                        <textarea className="form-control" id="formControlTextarea2" name="steps" ref="steps" rows="5" placeholder="Give steps..."/>
                                    </div>
                                </div>
                            </div>

                            <div className="form-group ">
                                <button type="button" className="btn btn-primary" onClick={this.handleSubmit}>Upload recipe</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        )
    }
}

module.exports = {
    uploadRecipe: UploadRecipe
};