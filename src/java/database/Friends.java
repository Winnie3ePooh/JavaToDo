package database;

import javax.persistence.*;
/**
 * Created by dnf on 08.11.15.
 */
@Entity
@Table(name = "FRIENDS")
public class Friends extends ObjectsDAO {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "FRIENDS_ID")
    private Long friendsId;
    /*
    @Column(name = "FRIEND_ID")
    private Long friendId;
    */
    @ManyToOne
    @JoinColumn(name = "FRIEND_REQUESTER")
    private User friendRequester;
    @ManyToOne
    @JoinColumn(name = "FRIEND_RECEIVER")
    private User friendReceiver;

    public Friends() {}
    /*
    public Friends(User friendRequester, User friendReceiver) {
        this.friendReceiver = friendReceiver;
        this.friendRequester = friendRequester;
    }
    */

    public void setFriendsId(Long friendsId) {
        this.friendsId = friendsId;
    }

    public Long getFriendsId() {
        return friendsId;
    }

    public void setFriendReceiver(User friendReceiver) {
        this.friendReceiver = friendReceiver;
    }

    public User getFriendReceiver() {
        return friendReceiver;
    }

    public void setFriendRequester(User friendRequester){
        this.friendRequester = friendRequester;
    }

    public User getFriendRequester() {
        return friendRequester;
    }
}
