package ufrn.imd.Web_II_AV1.model.dto;

import ufrn.imd.Web_II_AV1.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
