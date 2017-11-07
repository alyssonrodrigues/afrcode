// Import React
import React from "react";

// Import Spectacle Core tags
import {
    BlockQuote,
    CodePane,
    Deck,
    Heading,
    Image,
    Link,
    ListItem,
    List,
    Quote,
    Slide,
    Table,
    TableRow,
    TableHeaderItem,
    TableItem,
    Text
} from "spectacle";

// Import image preloader util
import preloader from "spectacle/lib/utils/preloader";

// Import theme
import createTheme from "spectacle/lib/themes/default";

// Require CSS
require("normalize.css");
require("spectacle/lib/themes/default/index.css");


const images = {
    city: require("../assets/city.jpg"),
    kat: require("../assets/kat.png"),
    logo: require("../assets/formidable-logo.svg"),
    markdown: require("../assets/markdown.png"),
    flux: require("../assets/flux.png"),
    fluxComplex: require("../assets/flux-complex.jpg"),
    mvc: require("../assets/mvc-scale.jpg")
};

preloader(images);

const theme = createTheme({
    primary: "white",
    secondary: "#1F2022",
    tertiary: "#03A9FC",
    quartenary: "#CECECE"
}, {
    primary: "Montserrat",
    secondary: "Helvetica"
});

export default class Presentation extends React.Component {
    render() {
        return (
            <Deck transition={["zoom", "slide"]} transitionDuration={500} theme={theme}>
                <Slide transition={["zoom"]} bgColor="secondary">
                    <Heading size={1} fit caps lineHeight={1} textColor="primary">React</Heading>
                    <Text margin="10px 0 0" textColor="tertiary" fit bold>is it a worth?</Text>
                </Slide>
                <Slide transition={["fade"]} bgColor="tertiary">
                    <BlockQuote>
                        <Quote textSize="xx-large">
                            React is a declarative, efficient, and flexible JavaScript library for building user
                            interfaces
                        </Quote>
                    </BlockQuote>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <Heading size={4} textColor="tertiary">Prior knowledge</Heading>
                    <Text margin="30px 0 0" textColor="primary" caps bold>JavaScript - ES6</Text>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <Heading size={5} textColor="tertiary">React usage statistics</Heading>
                    <List>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            <Link href="https://stackshare.io/react/in-stacks/" target="_blank" textColor="primary">
                                Who is using React?
                            </Link>
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            <Link href="https://github.com/facebook/react/wiki/Sites-Using-React" target="_blank" textColor="primary">
                                Sites using React
                            </Link>
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            <Link href="https://insights.stackoverflow.com/survey/2017#technology-most-loved-dreaded-and-wanted-frameworks-libraries-and-other-technologies" target="_blank" textColor="primary">
                                Most Loved Frameworks, Libraries and Other Technologies
                            </Link>
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            <Link href="http://stateofjs.com/2016/frontend/" target="_blank" textColor="primary">Front-end Frameworks</Link>
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            <Link href="https://insights.stackoverflow.com/survey/2017#technology-most-popular-languages-by-occupation" target="_blank" textColor="primary">
                                Most Popular Languages by Occupation
                            </Link>
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            <Link href="https://www.thoughtworks.com/de/radar/languages-and-frameworks" target="_blank" textColor="primary">
                                Languages and Frameworks
                            </Link>
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            <Link href="https://medium.com/unicorn-supplies/angular-vs-react-vs-vue-a-2017-comparison-c5c52d620176" target="_blank" textColor="primary">
                                Angular vs. React vs. Vue: A 2017 comparison
                            </Link>
                        </ListItem>
                    </List>
                </Slide>
                <Slide transition={["zoom"]} bgColor="tertiary">
                    <Heading size={1} fit caps lineHeight={1} textColor="primary">Fundamentals</Heading>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <Heading size={4} textColor="tertiary">Why React?</Heading>
                    <List>
                        <ListItem margin="20px 0 0" textSize="xx-large">Complexity of two-way data binding</ListItem>
                        <ListItem margin="20px 0 0" textSize="xx-large">
                            Bad UX from using "cascading updates" of DOM tree
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="xx-large">
                            A lot of data on a page changing over time
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="xx-large" bold>
                            Shift from MV* Frameworks mentality
                        </ListItem>
                    </List>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <BlockQuote>
                        <Quote textSize="xx-large">
                            MVC did not scale well for Facebook’s huge codebase. The main problem for them was the
                            bidirectional communication, where one change can loop back and have cascading effects
                            across the codebase (making things very complicated to debug and understand)
                        </Quote>
                    </BlockQuote>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <Image src={images.mvc}/>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <BlockQuote>
                        <Quote textSize="xx-large">
                            In general the flow inside the MVC pattern is not well defined. A lot of the bigger
                            implementations do it very differently
                        </Quote>
                    </BlockQuote>
                </Slide>
                <Slide transition={["fade"]} bgColor="tertiary" textColor="primary">
                    <BlockQuote>
                        <Quote textSize="xx-large">React challenges established best practices in tradicional MV* Frameworks</Quote>
                    </BlockQuote>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <BlockQuote>
                        <Quote textSize="xx-large">
                            Unidirectional flow of data between a system’s components: Flux Pattern
                        </Quote>
                    </BlockQuote>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <Image src={images.flux}/>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <Image src={images.fluxComplex}/>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <BlockQuote>
                        <Quote textSize="xx-large">
                            Controller, ModelView, and View are coupled: when you change one, you often have to change
                            the others
                        </Quote>
                    </BlockQuote>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <Heading size={5} textColor="tertiary">Build components, not templates</Heading>
                    <List>
                        <ListItem margin="20px 0 0" textSize="xx-large">UI Components are the cohesive units</ListItem>
                        <ListItem margin="20px 0 0" textSize="xx-large">
                            UI description and UI logic are tightly coupled and can be colocated - they shouldn't be
                            arbitrarily decoupled
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="xx-large" bold>
                            Full power of JavaScript to express UI
                        </ListItem>
                    </List>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <Table>
                        <TableRow>
                            <TableHeaderItem padding="10px" textSize="xx-large"/>
                            <TableHeaderItem padding="10px" textSize="xx-large">Templates</TableHeaderItem>
                            <TableHeaderItem padding="10px" textSize="xx-large" textColor="tertiary">
                                React Components
                            </TableHeaderItem>
                        </TableRow>
                        <TableRow>
                            <TableHeaderItem padding="10px" textSize="xx-large">Separation of Concerns</TableHeaderItem>
                            <TableItem padding="10px" textSize="xx-large">Technology (JS HTML)</TableItem>
                            <TableItem padding="10px" textSize="xx-large">Responsibility "Display UI"</TableItem>
                        </TableRow>
                        <TableRow>
                            <TableHeaderItem padding="10px" textSize="8">Semantic</TableHeaderItem>
                            <TableItem padding="10px" textSize="xx-large">
                                New concepts and micro-languages (scope, directives, iterators)
                            </TableItem>
                            <TableItem padding="10px" textSize="xx-large">HTML and JavaScript</TableItem>
                        </TableRow>
                        <TableRow>
                            <TableHeaderItem padding="10px" textSize="8">Expressiveness</TableHeaderItem>
                            <TableItem padding="10px" textSize="xx-large">Underpowered</TableItem>
                            <TableItem padding="10px" textSize="xx-large">Full power of JavaScript</TableItem>
                        </TableRow>
                    </Table>
                </Slide>
                <Slide transition={["fade"]} bgColor="tertiary" textColor="secondary">
                    <CodePane lang="js" source={require("raw-loader!./component.example")}/>
                </Slide>
                <Slide transition={["fade"]} bgColor="tertiary" textColor="secondary">
                    <CodePane lang="js" source={require("raw-loader!./component-with-no-jsx.example")}/>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <Heading size={5} textColor="tertiary">Component LifeCycle</Heading>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <Heading size={5} textColor="tertiary">Props</Heading>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <Heading size={5} textColor="tertiary">State</Heading>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <Heading size={5} textColor="tertiary">Re-render</Heading>
                    <Text margin="30px 0 0" textColor="primary" fit bold>
                        React re-render everything on every update, how?
                    </Text>
                </Slide>
                <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                    <Heading size={5} textColor="tertiary">Virtual DOM</Heading>
                    <Text margin="30px 0 0" textColor="primary" fit bold>
                        Whenever data changes, React builds a new virtual DOM subtree
                    </Text>
                    <List>
                        <ListItem margin="20px 0 0" textSize="x-large">diffs it with the old one</ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">
                            computes the minimal set of DOM mutations and puts them in a queue
                        </ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">and batch executes all updates</ListItem>
                        <ListItem margin="20px 0 0" textSize="x-large">and it is fast</ListItem>
                    </List>
                </Slide>
                <Slide transition={["zoom"]} bgColor="tertiary">
                    <CodePane lang="js" source={require("raw-loader!./mensagem-final.example")}/>
                </Slide>
            </Deck>
        );
    }
}
