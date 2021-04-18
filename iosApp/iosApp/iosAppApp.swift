//
//  iosAppApp.swift
//  iosApp
//
//  Created by Jose Flavio Quispe on 18/04/21.
//

import SwiftUI
import shared

@main
struct iosAppApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView(viewModel: ContentView.ViewModel(repository: SpaceXRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory())))
        }
    }
}
