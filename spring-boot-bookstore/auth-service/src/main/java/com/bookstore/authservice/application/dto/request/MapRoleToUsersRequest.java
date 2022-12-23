package com.bookstore.authservice.application.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapRoleToUsersRequest {

  private List<String> userNames;
}