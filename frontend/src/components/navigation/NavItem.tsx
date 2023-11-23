import { IconDefinition } from '@fortawesome/free-solid-svg-icons';
import { Link, useLocation } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

interface NavItemProps {
    icon: IconDefinition;
    text: string;
    path: string;
}

export default function NavItem(props: NavItemProps) {
    const location = useLocation();
    const activeClass = location.pathname === props.path ? 'nav-active' : '';
    return (
        <li className='nav-item'>
            <Link
                className={`nav-entry me-3 ${activeClass}`}
                to={props.path}
                data-toggle='tooltip'
                title={`${props.text}`}
            >
                <FontAwesomeIcon icon={props.icon} size='2x' />
            </Link>
            <div className='w-50'>
                <Link
                    className={`text-white nav-link d-block d-sm-none text-decoration-none ${activeClass}`}
                    to={props.path}
                >
                    {props.text}
                </Link>
            </div>
        </li>
    );
}
