import { type TimestampEntity } from '../timestampEntity.ts';

type HexColor = `#${string}`;

export interface TabbyUser extends TimestampEntity {
    id: number;
    username: string;
    roles: TabbyRole[];
    isAdmin: boolean;
}

export interface TabbyRole extends TimestampEntity {
    id: number;
    name: string;
    color: HexColor;
    admin: boolean;
    maxUpload: number;
}

export interface UserStore {
    user: TabbyUser;
}
