/**
 * Created by Paulina on 13.01.2018.
 */
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

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

class Recipes extends React.Component{

    componentDidMount() {
        client({
            method: 'GET',
            path: '/recipes',
            headers: {'Content-Type': 'application/json'}
        }).done(recipes => {
            console.log(recipes);
        });
    }

     render() {
//         var employees = this.props.employees.map(employee =>
//             <Employee key={employee._links.self.href} employee={employee}/>
//         );
         return (
             <div>recipes test</div>
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
         )
     }
 }
module.exports = {
    recipes: Recipes
};