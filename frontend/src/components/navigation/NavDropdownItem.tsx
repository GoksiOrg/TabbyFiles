import { Children, type PropsWithChildren, useEffect, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { type IconDefinition } from '@fortawesome/free-solid-svg-icons';

interface NavDropdownItemProps extends PropsWithChildren {
    text: string;
    icon: IconDefinition;
}

export default function NavDropdownItem(props: NavDropdownItemProps) {
    return (
        <li className='nav-item dropdown'>
            <a className='nav-entry me-3 dropdown-toggle' data-bs-toggle='dropdown' aria-expanded='false'>
                <FontAwesomeIcon icon={props.icon} size='2x' role='button' />
            </a>
            <div className='w-50'>
                <a
                    className='text-white nav-link d-block d-sm-none text-decoration-none dropdown-toggle'
                    data-bs-toggle='dropdown'
                    aria-expanded='false'
                >
                    {props.text}
                </a>
                <Menu>{props.children}</Menu>
            </div>
        </li>
    );
}

function Menu(props: PropsWithChildren) {
    const [isSmall, setSmall] = useState<boolean>(window.innerWidth < 576);

    useEffect(() => {
        const onResize = () => {
            const temp = window.innerWidth < 576;
            if (temp !== isSmall) setSmall(temp);
        };
        window.addEventListener('resize', onResize, false);

        return () => {
            window.removeEventListener('resize', onResize, false);
        };
    }, [isSmall]);

    return (
        <ul className={`dropdown-menu ${isSmall ? 'bg-body-primary-tabby' : ''}`}>
            {Children.map(props.children, child => {
                return <li>{child}</li>;
            })}
        </ul>
    );
}
