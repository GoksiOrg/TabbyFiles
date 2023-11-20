import { Children, PropsWithChildren, ReactNode } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IconDefinition } from '@fortawesome/free-solid-svg-icons';

interface NavDropdownItemProps {
    text: string;
    icon: IconDefinition;
    children?: ReactNode;
}

/*TODO: change dropdown color*/
export default function NavDropdownItem(props: NavDropdownItemProps) {
    return (
        <li className='nav-item dropdown' data-bs-theme='dark'>
            <a className={`nav-entry me-3 dropdown-toggle`} data-bs-toggle='dropdown' aria-expanded='false'>
                <FontAwesomeIcon icon={props.icon} size='2x' role='button' />
            </a>
            <div className='w-50'>
                <a
                    className={`text-white nav-link d-block d-sm-none text-decoration-none dropdown-toggle`}
                    data-bs-toggle='dropdown'
                    aria-expanded='false'
                >
                    {props.text}
                </a>
                <Menu children={props.children} />
            </div>
        </li>
    );
}

function Menu(props: PropsWithChildren) {
    return (
        <ul className='dropdown-menu'>
            {Children.map(props.children, child => {
                return <li>{child}</li>;
            })}
        </ul>
    );
}
