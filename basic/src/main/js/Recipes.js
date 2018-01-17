/**
 * Created by Paulina on 13.01.2018.
 */
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const Recipe = require('./Recipe').recipe;

class ShareInfo extends React.Component{

    constructor(props){
        super(props);
    }

    render(){
        return (
            <div>
                { this.props.success ? <p className="text-success">Shared!</p> : <p className="text-danger">User not found!</p> }
            </div>
        )
    }

}


class Recipes extends React.Component{

    constructor(props) {
        super(props);
        this.handleShare = this.handleShare.bind(this);
        this.state = {
            recipes : [],
            showedDetails: false,
            currentViewedRecipe: {},
            shared: false,
            sharedSuccess: true
        };

    }

    handleShare(e) {
        var fusername = ReactDOM.findDOMNode(this.refs["username"]).value.trim();


        client({
            method: 'GET',
            path: '/users/exists',
            params: {username: fusername},
            headers: {'Content-Type': 'application/json'}
        }).then(response => {
            console.log(response);

            if(response.status.code === 200) {
                client({
                    method: 'GET',
                    path: '/recipes/share',
                    params: {username: fusername, recipeId: this.state.currentViewedRecipe.id},
                    headers: {'Content-Type': 'application/json'}
                }).done(response => {
                   console.log(response);
                    this.setState({
                        shared: true,
                        sharedSuccess: true
                    });
                });
            }

        }).catch(error => {
            this.setState({
                shared: true,
                sharedSuccess: false
            });
        });


    }

    componentDidMount() {
        client({
            method: 'GET',
            path: '/recipes',
            headers: {'Content-Type': 'application/json'}
        }).done(recipes => {
            console.log(recipes);

            this.setState({
                recipes : recipes.entity
            });


        });
    }

     render() {

         var handleClick = (e, recipe) => {
             // access to e.target here
             console.log(recipe);

             this.setState({
                 showedDetails: true,
                 currentViewedRecipe: recipe
             });

         };
         var recipes = this.state.recipes.map(recipe =>
             <a className="list-group-item list-group-item-action" onClick={(e) => handleClick(e, recipe)}>{recipe.name}</a>
         );
         return (
             <div className="row recipes-container">
                 <div className="col-md-4">
                     <div className="list-group">
                         {recipes}
                     </div>
                 </div>
                 <div className="col-md-8">
                   {this.state.showedDetails ?
                       <div>
                       <Recipe recipe={this.state.currentViewedRecipe}/>
                           <div className="container w-75 login-button">
                               <div className="form-group row">
                                   <div className="col-md-4">
                                       <input type="text" className="form-control" placeholder="Type your friend username..." ref="username"/>
                                   </div>
                                   <div className="col-md-8">
                                       <button type="button" className="btn btn-primary" onClick={this.handleShare}>Share</button>
                                   </div>
                                   {this.state.shared ? <ShareInfo success={this.state.sharedSuccess} /> : null }
                               </div>
                            </div>
                       </div>
                       : null}
                 </div>
             </div>
         )
     }
 }

module.exports = {
    recipes: Recipes
};